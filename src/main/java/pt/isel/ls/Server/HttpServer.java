package pt.isel.ls.Server;
        import pt.isel.ls.Commands.Command;
        import pt.isel.ls.Commands.GetCommand;
        import pt.isel.ls.Exceptions.NoSuchCommandException;
        import pt.isel.ls.Logic.Arguments;
        import pt.isel.ls.Manager.CommandManager;
        import pt.isel.ls.Manager.ConnectionManager;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.nio.charset.Charset;
        import java.sql.SQLException;
        import java.text.ParseException;

        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class HttpServer extends HttpServlet{
    private final CommandManager manager;

    public HttpServer(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String [] path = new String[]{req.getMethod(),req.getPathInfo()};

        String respBody = null;
        try {
            Arguments args= new Arguments();
            Command cmd  = manager.searchCommand(path, args);
            Object result =cmd.execute(args,manager.conManager.getConection());
            if(cmd instanceof GetCommand) {
                respBody = manager.getResultString(cmd, result);
            }

        } catch (NoSuchCommandException e) {
            System.out.println("Can't find command in: " + req.getMethod() + ' ' + req.getPathInfo());
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            resp.setStatus(404);
            return;
        }

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format("text/html; charset=%s",utf8.name()));
        byte[] respBodyBytes = respBody.getBytes(utf8);
        resp.setStatus(200);
        resp.setContentLength(respBodyBytes.length);
        OutputStream os = resp.getOutputStream();
        os.write(respBodyBytes);
        os.close();

    }
}