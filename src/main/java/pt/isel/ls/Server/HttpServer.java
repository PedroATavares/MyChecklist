package pt.isel.ls.Server;
        import pt.isel.ls.Commands.Command;
        import pt.isel.ls.Commands.GetCommand;
        import pt.isel.ls.Exceptions.NoSuchCommandException;
        import pt.isel.ls.Exceptions.NoSuchElementException;
        import pt.isel.ls.Logic.Arguments;
        import pt.isel.ls.Manager.CommandManager;
        import pt.isel.ls.Manager.ConnectionManager;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.nio.charset.Charset;
        import java.sql.Connection;
        import java.sql.SQLException;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.Map;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class HttpServer extends HttpServlet{
    private final CommandManager manager;
    private Logger logger;
    private DateFormat dateFormat;
    private HttpStatusCode statusCode;

    public HttpServer(CommandManager manager) {
        this.manager = manager;
        logger = LoggerFactory.getLogger(HttpServer.class);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req,resp);
    }

    public void doMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ArrayList<String> path= new ArrayList<>();
        path.add(req.getMethod());
        path.add(req.getPathInfo());
        Arguments arguments= new Arguments();
        req.getParameterMap().forEach((key,value)-> arguments.addArgument(key,value[0]));
        Command cmd=null;

        String s=req.getHeader("accept");
        if (s!=null) manager.addHeader("accept",s);

        String respBody = null;
        try {

            respBody = manager.searchAndExecute(path.toArray(new String[path.size()]),arguments);
            cmd = manager.getLastCommand();

        } catch (NoSuchCommandException e) {
            resp.setStatus(statusCode.NotFound.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.NotFound.valueOf());
            return;
        } catch (SQLException e) {
            resp.setStatus(statusCode.InternalServerError.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.InternalServerError.valueOf());
            return;
        } catch (ParseException e) {
            resp.setStatus(statusCode.BadRequest.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.BadRequest.valueOf());
            return;
        } catch (NoSuchElementException e) {
            resp.setStatus(statusCode.NotFound.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.NotFound.valueOf());
            return;
        }catch (NumberFormatException e){
            resp.setStatus(statusCode.BadRequest.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.BadRequest.valueOf());
            return;
        }

        if (cmd instanceof GetCommand){
            Charset utf8 = Charset.forName("utf-8");
            setContentType(resp);
            byte[] respBodyBytes = respBody.getBytes(utf8);
            resp.setStatus(statusCode.Ok.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | Status code " + statusCode.Ok.valueOf() );
            resp.setContentLength(respBodyBytes.length);
            OutputStream os = resp.getOutputStream();
            os.write(respBodyBytes);
            os.close();
        }else{
            String destPath = arguments.arguments.get("reload");
            if(destPath == null) destPath = arguments.arguments.get("dest")+ respBody;
            resp.addHeader("Location",destPath);
            resp.setStatus(statusCode.SeeOther.valueOf());
            logger.info( dateFormat.format(new Date()) + " | " + req.getMethod() + req.getPathInfo() + " | ERROR, status code " + statusCode.SeeOther.valueOf());
        }
    }

    private void setContentType(HttpServletResponse resp) {
        Charset utf8 = Charset.forName("utf-8");
        String accept=manager.headers.get("accept");

        switch (accept) {
            case"application/json":
                resp.setContentType(String.format("application/json; charset=%s",utf8.name()));
                break;
            case"text/html":
                resp.setContentType(String.format("text/html; charset=%s",utf8.name()));
                break;
            case"text/plain":
                resp.setContentType(String.format("text/plain; charset=%s",utf8.name()));
                break;
            default:
                resp.setContentType(String.format("text/html; charset=%s",utf8.name()));
                break;
        }
    }
}