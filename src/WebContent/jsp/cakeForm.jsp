<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!doctype html>
<html lang="en">
<%@include file="head.jsp"%>
<body>
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <%@ include file="menu.jsp"%>
        <main class="mdl-layout__content">
            <div class="page-content">
                <div class="mdl-grid center-items">
                    <div class="mdl-cell mdl-cell--4-col">
                        <div class="mdl-card mdl-shadow-6dp">
                            <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                                <h2 class="mdl-card__title-text">
                                    <:if test="${cake != null}">Edit Cake</:if>>
                                    <:if test="${cake == null}">Add new Cake</:if>>
                                </h2>
                            </div>
                            <div class="mdl-card__supporting-text">
                                <:if test="${cake != null}">
                                    <form name="myForm" action="update" method="post" onsubmit="return validateForm()">
                                </:if>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </main>

    </div>
</body>
</html>
