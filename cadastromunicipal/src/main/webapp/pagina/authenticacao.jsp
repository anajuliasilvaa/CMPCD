<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="pt-br">

        <head>
            <meta charset="UTF-8">
            <title>CMPCD</title>
            <link rel="stylesheet" type="text/css" href="/cadastromunicipal/pagina/css/authenticacao.css">
            <link rel="stylesheet" type="text/css" href="/cadastromunicipal/pagina/css/authenticacaoGlobal.css">
        </head>

        <body>
            <header>
                <div class="ordem-header">
                    <div class="primeiro-header">
                        <a href="/cadastromunicipal/page?pagina=inicial" class="align">Lorem ipsum dolor</a>
                    </div>
                </div>

            </header>

            <section class="align">

                <form class="section-template align" action="/cadastromunicipal/authentificacao" method="POST">
                    <h2>Authenticação</h2>
                    <div class="formulario">
                        <div class="ordem-formulario">
                            <label for="email">email</label>
                            <label for="senha">senha</label>
                        </div>
                        <div class="ordem-formulario">
                            <input type="text" name="email" id="email" required autofocus>
                            <input type="password" name="senha" id="senha" required minlength="8">
                        </div>
                    </div>

                    <input type="hidden" name="acao" value="${param.acao}">


                    <button type="submit" class="btt-section-template align green-color">Authentificar</button>

                </form>

            </section>
            <footer class="align">

                <div>

                </div>
                <div>
                    <ul>
                        <li>Instagram</li>
                        <li>Facebook</li>
                        <li>Contato</li>
                        <li>etc...</li>
                    </ul>
                </div>


            </footer>

        </body>

        </html>