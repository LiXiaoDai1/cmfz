<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    $(function(){
        KindEditor.create('#editor_id',{
            width : '700px',
            uploadJson:'${pageContext.request.contextPath}/guru/upload',
            fileManagerJson:'${pageContext.request.contextPath}/guru/showAll',
            allowFileManager:true,
            filePostName:'file',
            afterBlur:function(){
                this.sync();
            }
        });
    })
    function addArticle(){
        $.ajax({
            url:"${pageContext.request.contextPath}/guru/addArticle",
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),
            success:function(){

            }
        })
    }
</script>


<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
   文件上传
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <form action="javascript:void(0);" id="articleForm">
                <div class="modal-body">
                    <input type="text" name="title">
                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addArticle()">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function(){
        $("#albumTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/guru/queryAll",
            datatype:"json",
            colNames:["编号","名称","封面","状态"],
            colModel:[
                {name:"id"},
                {name:"name",editable:true},
                {name:"profile",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/guruPic/"+cellvalue+"'/>";
                    }},
                {name:"status",editable:true}],
            pager:"albumPager",
            rowNum:3,
            rowList:[3,5,7],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/guru/edit",
            height:"100%",
            multiselect:true,
            rownumbers:true,
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                // we pass two parameters
                // subgrid_id is a id of the div tag created whitin a table data
                // the id of this elemenet is a combination of the "sg_" + id of the row
                // the row_id is the id of the row
                // If we wan to pass additinal parameters to the url we can use
                // a method getRowData(row_id) - which returns associative array in type name-value
                // here we can easy construct the flowing
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${pageContext.request.contextPath}/chapter/queryAll?id=" + row_id,
                        datatype : "json",
                        styleUI:"Bootstrap",
                        autowidth:true,
                        colNames : [ '编号', '专辑编号', '名称', '大小','下载路径','上传时间','操作' ],
                        colModel : [
                            {name : "id"},
                            {name : "a_id"},
                            {name : "title",editable:true},
                            {name : "size",editable:true},
                            {name : "downPath",editable:true,edittype:"file"},
                            {name : "uploadTime",editable:true,edittype:"date"},
                            {name : "downPath",edittype:"file",formatter:function(cellvalue, options, rowObject){
                                    return "<a onclick=\"\" class=\"btn btn-primary\"href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"
                                }},
                        ],
                        rowNum : 20,
                        pager : pager_id,
                        rowList:[3,5,7],
                        viewrecords:true,
                        autowidth:true,
                        editurl:"${pageContext.request.contextPath}/chapter/edit?a_id=" + row_id,
                        height : '100%'

                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit :false,
                        add : true,
                        del : false
                    },{
                        //修改的部分
                    },
                    {
                        //添加的部分
                        closeAfterAdd:true,
                        afterSubmit:function(response){
                            $.ajaxFileUpload({
                                url:"${pageContext.request.contextPath}/chapter/upload",
                                fileElementId:"downPath",
                                data:{"id":response.responseText},
                                type:"post",
                                success:function(){
                                    $("#albumTable").trigger("reloadGrid");
                                }
                            })
                            return "[true]";
                        }
                    }
                );
            },
        }).jqGrid("navGrid","#albumPager",{},{
            //修改的部分
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"coverUrl",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#albumTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        },{
            //添加的部分
            closeAfterAdd:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"coverUrl",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#albumTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        })
    })
</script>
<table id="albumTable"></table>
<div id="albumPager"></div>