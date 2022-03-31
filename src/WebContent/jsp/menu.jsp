<%@page import="java.util.Calendar"%>

<header class="mdl-layout_header">
    <div class="mdl-layout_header-row">
        <span class="mdl-layout-title">Cakes and Recipes Management System</span>

        <div class="mdl-layout-spacer"></div>

        <tag:formatDate date="<%=Calendar.getInstance().getTime()%>"
            format="dd-MM-YYYY hh:mm">n</tag:formatDate>

        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="/PSMS/new">Add New Cake</a>
            <a class="mdl-navigation__link" href="/PSMS/list">List all Cakes</a>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">PSMS</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="/PSMS/new"> Add new Recipe</a>
        <a class="mdl-navigation__link" href="/PSMS/list"> List all Recipes</a>
    </nav>
</div>