<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TetraSoft - Teste</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="util/css/estilo.css">
        <link rel="icon" href="util/img/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="util/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="util/css/font-awesome.css">

        <script src="util/js/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="util/css/sweetalert.css">
    </head>
    <body>

        <!--Título e logo-->
        <div class="container">
            <div class="row">
                <div class="form group col-md-6">
                    <img src="util/img/logoTetraSoft.png" alt="Logo TetraSoft" > 
                    <h1>SISTEMA DEMONSTRATIVO</h1>
                </div>
                <div class="form group col-md-3">
                    <h3>Menu</h3>
                    <a href="cadastro.jsp" class="btn btn-primary">Cadastro</a>
                    <a href="pesquisa.jsp" class="btn btn-primary">Pesquisa</a>
                    <hr/>
                </div>
            </div>
        </div>


        <c:choose>
            <c:when test="${validacaoCadastro == 4}">
                <script>
                    sweetAlert("Oops...", "Por favor, pesquise somente um NOME ou um E-MAIL.", "error");
                </script>
            </c:when>
            <c:when test="${validacaoCadastro == 5}">
                <script>
                    sweetAlert("Registro não encontrado!", "Desculpe, não conseguimos encontrar ${validacaoVar} em nossa base de dados.", "error");
                </script>
            </c:when>
        </c:choose>



        <!--Formulário de cadastro de clientes-->
        <div class="container">

            <div class="row">
                <div class="form group col-md-8">
                    <form class="form-inline" method="post" action="listarCliente">
                        <h3>Pesquise:</h3>
                        Nome:<input type="text" name="nomeBusca" class="form-control" maxlength="35" placeholder="">
                        E-mail:<input type="text" name="emailBusca" class="form-control" maxlength="35" placeholder="">
                        <button type="submit" class="btn btn-warning glyphicon glyphicon-refresh"> Buscar</button>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="form group col-md-9"> 
                    <hr/>   
                    <table class="table">
                        <thead>
                            <tr>
                                
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Telefone</th>
                                <th>Ações</th>
                            </tr>
                        </thead>

                        <!--FOR para percorrer LISTA DE CLIENTE e exibir na tabela-->
                        <c:forEach items="${listaDeClientes}" var="clientes">
                            <tbody>
                                <tr>
                                    
                                    <td>${clientes.nome}</td>
                                    <td>${clientes.email}</td>
                                    <td>${clientes.telefone}</td>

                                    <td>  
                                        <!--BOTÃO DE CADA LINHA PARA EDITAR-->
                                        <form action="listarCliente" method="get"  style="display: inline;">
                                            <input type="hidden" name="id" value="${clientes.id}">  
                                            <button type="submit" class="btn btn-warning btn-sm glyphicon glyphicon-pencil"></button>
                                        </form>
                                        <!--BOTÃO DE CADA LINHA PARA DELETAR-->
                                        <form method="post" action="deletarCliente"  style="display: inline;">
                                            <input type="hidden" name="id" value="${clientes.id}">
                                            <button type="submit" class="btn btn-danger btn-sm glyphicon glyphicon-remove"></button>
                                        </form>

                                        <button type="button" onclick="Visualizar()" class="btn btn-primary btn-sm glyphicon glyphicon-zoom-in"></button>
                                        <script>
                                            function Visualizar() {
                                                swal("${clientes.nome}", "Telefone: ${clientes.telefone} \n Para mais informações entre em contato.");
                                            }
                                        </script>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach> <!--FIM DO FOR-->
                    </table>
                </div>
            </div>
            <script type="text/javascript" src="util/js/bootstrap.min.js"></script>
    </body>
</html>