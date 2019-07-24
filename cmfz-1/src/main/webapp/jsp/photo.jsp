<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<div class="page-header">
    <h1>轮播图管理</h1>
</div>
<script>
    $(function(){
        $("#carouselTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/banner/queryAll",
            datatype:"json",
            colNames:["编号","名称","图片","状态","上传时间"],
            colModel:[
                {name:"id"},
                {name:"headline",editable:true},
                {name:"path",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/photo/"+cellvalue+"'/>";
                    }},
                {name:"state",editable:true},
                {name:"createTime",editable:true,edittype:"date"}],
            pager:"carouselPager",
            rowNum:3,
            rowList:[3,5,7],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/banner/edit",
            height:"100%",
            multiselect:true,
            rownumbers:true
        }).jqGrid("navGrid","#carouselPager",{},{
            //修改的部分
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/banner/upload",
                    fileElementId:"path",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#carouselTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        },{
            //添加的部分
            closeAfterAdd:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/banner/upload",
                    fileElementId:"path",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#carouselTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        })
    })
</script>
<table id="carouselTable"></table>
<div id="carouselPager"></div>