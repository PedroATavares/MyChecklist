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
        import java.sql.SQLException;
        import java.text.ParseException;
        import java.util.ArrayList;
        import java.util.Map;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class HttpServer extends HttpServlet{
    private final CommandManager manager;

    public HttpServer(CommandManager manager) {
        this.manager = manager;
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

        String s=req.getHeader("accept");
        if (s!=null) manager.addHeader("accept",s);

        String respBody = null;
        try {
            respBody = manager.searchAndExecute(path.toArray(new String[path.size()]),arguments);

        } catch (NoSuchCommandException e) {
            resp.setStatus(404);
            return;
        } catch (SQLException e) {
            resp.setStatus(500);
            return;
        } catch (ParseException e) {
            resp.setStatus(400);
            return;
        } catch (NoSuchElementException e) {
            resp.setStatus(404);
            return;
        }catch (NumberFormatException e){
            resp.setStatus(400);
            return;
        }

        Charset utf8 = Charset.forName("utf-8");
        setContentType(resp);
        byte[] respBodyBytes = respBody.getBytes(utf8);
        resp.setStatus(200);
        resp.setContentLength(respBodyBytes.length);
        OutputStream os = resp.getOutputStream();
        os.write(respBodyBytes);
        os.close();

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