<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina-Salvar Cadastro</title>
</head>
<body>
<%
String snome = request.getParameter("nome");
String semail = request.getParameter("email");
String ssenha = request.getParameter("senha");

/* /* Usuario usu = new Usuario();
usu.setNome(snome);
usu.setEmail(semail);
usu.setSenha(ssenha);

UsuarioDAO usuDAO = new UsuarioDAO();
usuDAO.cadastro(usu);  */ 

%>

<h1> Salvo com Sucesso!</h1>

</body>
</html>