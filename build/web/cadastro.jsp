<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TetraSoft - Teste</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="icon" href="util/img/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="util/css/bootstrap.min.css">   
        <link rel="stylesheet" type="text/css" href="util/css/estilo.css">

        <script type="text/javascript" src="util/js/jquery-3.2.1.min.js"/></script>
        <script type="text/javascript" src="util/js/jquery.maskedinput.js"/></script>

        <script src="util/js/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="util/css/sweetalert.css">
        <link rel="stylesheet" type="text/css" href="util/css/font-awesome.css">
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

                <a href="listarCliente" class="btn btn-primary">Pesquisa</a>

                <hr/>
            </div>
        </div>
    </div>

    <!--Mensagem de SUCESSO e ERRO, utilizando JS + SweetAlert-->
    <c:choose>
        <c:when test="${validacaoCadastro == 1}">
            <script>
                swal("Parabéns!", "Cliente cadastrado com êxito!", "success");
            </script>
        </c:when>
        <c:when test="${validacaoCadastro == 2}">
            <script>
                sweetAlert("Atualizado!", "Cliente atualizado com sucesso!", "success");
            </script>
        </c:when>
        <c:when test="${validacaoCadastro == 3}">
            <script>
                sweetAlert("Oops...", "Alguma coisa deu errada!", "error");
            </script>
        </c:when>
        <c:when test="${validacaoCadastro == 6}">
            <script>
                sweetAlert("E-mail já cadastrado!", "Por favor tente novamente com um novo e-mail.", "error");
            </script>
        </c:when>
    </c:choose>

    <!--Formulário de cadastro de clientes-->
    <div class="container">
        <fieldset><legend>Cadastro de Cliente</legend>
            <form class="form-horizontal" method="post" action="salvarCliente">
                <input type="hidden" name="id" value="${clienteUpdate.id}"><!--Campo do ID para alteração-->

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label>Nome:</label>
                        <input type="text" name="nome" value="${clienteUpdate.nome}" class="form-control" maxlength="40" placeholder="Nome completo" required>
                    </div>                    
                </div>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label>E-mail:</label>
                        <input type="text" name="email" value="${clienteUpdate.email}" class="form-control" maxlength="40" placeholder="seuemail@teste.com" required>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label>Endereço:</label>
                        <input type="text" name="endereco" value="${clienteUpdate.endereco}" class="form-control" maxlength="50" placeholder="Ex: Av. Exemplo de teste, 000, 2º andar" required>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label for="campoTell">Telefone:</label>
                        <input type="text" id="campoTell" name="tell" value="${clienteUpdate.telefone}" maxlength="14" class="form-control" placeholder="(__) ____-____" required>
                    </div>
                </div>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('#campoTell').mask('(99) 9999-9999');
                    });
                </script>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label>Sexo:</label>
                        <input type="radio" name="sexo" value="M" ${clienteUpdate.sexo == "M"?'checked':''} required>Masculino
                        <input type="radio" name="sexo" value="F" ${clienteUpdate.sexo == "F"?'checked':''} >Feminino
                    </div>
                </div>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <label>NewsLetter:</label>
                        <input type="checkbox" name="newsLetter" value="S" ${clienteUpdate.newsLetter == "S"?'checked':''} >
                    </div>
                </div>

                <div class="form-group row">
                    <div class="form group col-md-4">
                        <button type="submit" class="btn btn-success glyphicon glyphicon-floppy-save"> Salvar</button>
                        <button type="reset" class="btn btn-warning glyphicon glyphicon-remove"> Cancelar</button>
                    </div>
                </div>
                </div>
            </form>
        </fieldset>
    </div>
</body>
</html>