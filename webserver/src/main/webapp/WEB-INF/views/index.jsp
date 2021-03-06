<%@ page import="ru.exercise.webserver.domain.model.ComputerPart" %>
<%@ page import="ru.exercise.webserver.domain.model.ComputerPartListRequest" %>
<%@ page import="ru.exercise.webserver.domain.model.ComputerPartListResponse" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
    final ComputerPartListResponse list = (ComputerPartListResponse) session.getAttribute("list");
    final ComputerPartListRequest listRequest = (ComputerPartListRequest) session.getAttribute("request");
    final String filterName = listRequest.getFilter() == null ? "" : listRequest.getFilter().getName();
    final String filterValue = listRequest.getFilter() == null ? "" : listRequest.getFilter().getValue();
    final Integer pageNumber = listRequest.getPageNumber();
    final Long totalCount = list.getTotalCount();
    final Long pagesCount = totalCount / 10 + (totalCount % 10 == 0 ? 0 : 1);
    final String filterByNameValue = "name".equals(filterName) ? filterValue : "";
    
    final String contextPath = request.getContextPath();

    final Integer computerCount = list.getRequiredCount() == null ? 0 : list.getRequiredCount();

%>
<html>
<head>
    <meta charset="UTF-16">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<body>
<style>
    body {
        padding: 16px;
    }

    .col.col-md-2.greyBox {
        padding-top: .75rem;
        padding-bottom: .75rem;
        background-color: rgba(86, 61, 124, .15);
        border: 1px solid rgba(86, 61, 124, .2);
    }

    .col.col-md-1.greyBox {
        padding-top: .75rem;
        padding-bottom: .75rem;
        background-color: rgba(86, 61, 124, .15);
        border: 1px solid rgba(86, 61, 124, .2);
    }
</style>
<script>
    document.charset = "UTF-16";
    function setIdForUpdate(id) {
        var updateForm = document.forms.updateForm;
        updateForm.elements.id.value = id;
    }

    function setRequiredForUpdate(elementId) {
        var updateForm = document.forms.updateForm;
        updateForm.elements.required.value = document.getElementById(elementId).checked;
    }

    function setNameForUpdate(elementId) {
        var updateForm = document.forms.updateForm;
        updateForm.elements.name.value = document.getElementById(elementId).value;
    }

    function setQuantityForUpdate(elementId) {
        var updateForm = document.forms.updateForm;
        updateForm.elements.quantity.value = document.getElementById(elementId).value;
    }

    function submitUpdateFormForNewPart() {
        setIdForUpdate(null);
        setNameForUpdate("nameForNewPart");
        setRequiredForUpdate("requiredForNewPart");
        setQuantityForUpdate("quantityForNewPart");
        document.forms.updateForm.submit();
    }

    function submitUpdateFormForChangedPart() {
        setNameForUpdate("computerPartNameInput");
        setRequiredForUpdate("computerPartRequiredInput");
        setQuantityForUpdate("computerPartQuantityInput");
        document.forms.updateForm.submit()
    }

    function listByName() {
        var listForm = document.forms.listForm;
        listForm.filterName.value = "name";
        listForm.filterValue.value = document.getElementById("filterByName").value;
        listForm.submit();
    }

    function listPageNumber(pageNumber) {
        var listForm = document.forms.listForm;
        listForm.pageNumber.value = pageNumber;
        listForm.submit();
    }

    function listRequired(value) {
        var listForm = document.forms.listForm;
        listForm.filterName.value = "required";
        listForm.filterValue.value = value;
        listForm.submit();
    }

    function listAll() {
        var listForm = document.forms.listForm;
        listForm.filterName.value = "";
        listForm.submit();
    }

    function deleteComputerPart(id, name) {
        if (!confirm("Вы действительно хотите удалить " + name + "?")) {
            return;
        }
        var deleteForm = document.forms.deleteForm;
        deleteForm.filterName.value = "<%=filterName%>";
        deleteForm.filterValue.value = "<%=filterValue%>";
        deleteForm.pageNumber.value = "<%=pageNumber%>"
        document.getElementById("deleteId").value = id;
        deleteForm.submit();
    }

    function prepareForUpdate(id) {
    }

</script>
<div>
    <h2>Складские детали</h2>
    <form action="<%=contextPath%>/list" name="listForm" id="listForm" method="post" accept-charset="UTF-16">
        <input type="hidden" name="filterName" value="<%=filterName%>"/>
        <input type="hidden" name="filterValue" value="<%=filterValue%>"/>
        <input type="hidden" name="pageNumber" value="<%=pageNumber%>"/>
    </form>
    <form action="<%=contextPath%>/update" method="post" name="updateForm" id="updateForm" accept-charset="UTF-16">
        <input type="hidden" name="filterName" value="<%=filterName%>"/>
        <input type="hidden" name="filterValue" value="<%=filterValue%>"/>
        <input type="hidden" name="pageNumber" value="<%=pageNumber%>"/>
        <input type="hidden" name="name" value=""/>
        <input type="hidden" name="required" value=""/>
        <input type="hidden" name="quantity" value="0"/>
        <input type="hidden" name="id" value=""/>
    </form>
    <form action="<%=contextPath%>/delete" method="post" name="deleteForm" id="deleteForm" accept-charset="UTF-16">
        <input type="hidden" name="filterName" value="<%=filterName%>"/>
        <input type="hidden" name="filterValue" value="<%=filterValue%>"/>
        <input type="hidden" name="pageNumber" value="<%=pageNumber%>"/>
        <input type="hidden" name="id" id="deleteId" value=""/>
    </form>
    <form action="<%=contextPath%>/list">
        Наименование детали <input type="text" id="filterByName" value="<%=filterByNameValue%>"/>
        <button type="button" class="btn btn-default" aria-label="Left Align" onclick="javascript:listByName()">Поиск
        </button>
        <br/>
        Выбрать: <a href="javascript:listAll()">все</a>
        <a href="javascript:listRequired(true)">основные</a>
        <a href="javascript:listRequired(false)">опции</a>
    </form>
    <br/>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="computerPartNameInput" class="col-form-label">Наименование детали:</label>
                            <input type="text" class="form-control" id="computerPartNameInput">
                        </div>
                        <div class="form-group">
                            <label for="computerPartRequiredInput" class="col-form-label">Деталь основная?</label>
                            <input type="checkbox" class="form-control" id="computerPartRequiredInput">
                        </div>
                        <div class="form-group">
                            <label for="computerPartQuantityInput" class="col-form-label">Количество:</label>
                            <input type="text" class="form-control" id="computerPartQuantityInput">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:submitUpdateFormForChangedPart()">
                        Сохранить
                    </button>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-hover table-bordered table-sm" style="text-align: center">
        <thead>
        <tr style="background-color: #d4dce8">
            <th scope="col">Номер</th>
            <th scope="col">Наименование</th>
            <th scope="col">Основная</th>
            <th scope="col">Кол-во</th>
            <th scope="col">Изменить</th>
            <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody>
        <%

            for (final ComputerPart computerPart : list.getComputerParts()) {
                final Long id = computerPart.getId();
                final String name = computerPart.getName();
                final Integer quantity = computerPart.getQuantity();
                final Boolean required = computerPart.getRequired();
                final String srcPath = computerPart.getRequired() ? "enabled.svg" : "disabled.svg";
        %>
        <tr>
            <td><%=id%>
            </td>
            <td>
                <%=name%>
            </td>
            <td>
                <img src="resources/img/<%=srcPath%>" style="width: 24px"/>
            </td>
            <td><%=quantity%>
            </td>
            <td>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                        data-computerpartid="<%=id%>"
                        data-computerpartname="<%=name%>"
                        data-computerpartrequired="<%=required%>"
                        data-computerpartquantity="<%=quantity%>"
                >
                    изменить
                </button>
            </td>
            <td>
                <button type="button" class="btn btn-warning" aria-label="Left Align"
                        onclick="javascript:deleteComputerPart(<%=id%>, '<%=name%>')">удалить</i>
                </button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr>
            <td style="text-align: center" colspan="6">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <%
                            for (int i = 1; i < pagesCount + 1; i++) {
                        %>
                        <li class="page-item"><a class="page-link" href="#"
                                                 onclick="javascript:listPageNumber(<%=i%> - 1)"><%=i%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </nav>
            </td>
        </tr>
        </tfoot>
    </table>
    <br/>
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col col-md-2 greyBox">
                Всего можно собрать
            </div>
            <div class="col-md-auto" style="
            vertical-align: middle;
            text-align: center;
            margin-top: 16px;
            font-size: 20px;
            width: 64px;"
            >
                <%=computerCount%>
            </div>
            <div class="col col-md-2 greyBox">
                компьютеров
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <h3>Добавить деталь</h3>
    <table style="border-width: 0">
        <tbody>
        <tr style="padding-top: 4px">
            <td>Наименование детали</td>
            <td style="padding-left: 8px;"><input type="text" id="nameForNewPart"
                                                  onchange="javascript:setNameForUpdate('nameForNewPart')"/></td>
        </tr>
        <tr style="padding-top: 4px">
            <td>Деталь основная?</td>
            <td style="padding-left: 8px;"><input type="checkbox" id="requiredForNewPart"
                                                  onchange="javascript:setRequiredForUpdate('requiredForNewPart')"/></td>
        </tr>
        <tr style="padding-top: 4px">
            <td>Количество деталей</td>
            <td style="padding-left: 8px;"><input type="text" id="quantityForNewPart" value="0"
                                                  onchange="javascript:setQuantityForUpdate('quantityForNewPart')"/></td>
        </tr>
        </tbody>
    </table>


    <br/>
    <button type="button" class="btn btn-default" aria-label="Left Align" onclick="submitUpdateFormForNewPart()">
        Добавить
    </button>
    <br/>

</div>
<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('computerpartid'); // Extract info from data-* attributes
        var name = button.data('computerpartname'); // Extract info from data-* attributes
        var required = button.data('computerpartrequired'); // Extract info from data-* attributes
        var quantity = button.data('computerpartquantity'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        setIdForUpdate(id);
        modal.find('.modal-title').text('Редактирование детали: ' + name);
        modal.find('.modal-body #computerPartNameInput').val(name);
        modal.find('.modal-body #computerPartRequiredInput').prop("checked", required);
        modal.find('.modal-body #computerPartQuantityInput').val(quantity);
    });

</script>
</body>
</html>
