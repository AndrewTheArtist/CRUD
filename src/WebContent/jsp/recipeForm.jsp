<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<%@include file="head.jsp" %>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%@ include file="menu.jsp" %>
    <main class="mdl-layout__content">
        <div class="page-content">
            <div class="mdl-grid center-items">
                <div class="mdl-cell mdl-cell--4-col">
                    <div class="mdl-card mdl-shadow-6dp">
                        <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                            <h2 class="mdl-card__title-text">
                                <c:if test="${recipe != null}">Edit Recipe</c:if>
                                <c:if test="${recipe == null}">Add new Recipe</c:if>
                            </h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <c:if test="${recipe != null}"><form name="myForm" action="update" method="post" onsubmit="return validateForm()"></c:if>
                            <c:if test="${recipe == null}"><form name="myForm" action="insert" method="post" onsubmit="return validateForm()"></c:if>
                            <c:if test="${recipe != null}"><input type="hidden" name="id" value="<:out value='${recipe.id}'/>"/></c:if>

                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="name"
                                    value="<c:out value='${recipe.name}' /> " id="name" />
                                <label class="mdl-textfield__label" for="name">Name</label>
                            </div>

                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="description"
                                    value="<c:out value='${recipe.description}' /> " id="description" />
                                <label class="mdl-textfield__label" for="description">Description</label>
                            </div>

                            <input type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" value="save">

                            </form>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </main>

</div>
<script type="text/javascript">
    function validateForm() {
        let x = document.forms["myForm"]["quantity"].value;
        if(x === "") {
            alert("Quantity must be filled out");
            return false;
        }
    }
</script>
</body>
</html>
