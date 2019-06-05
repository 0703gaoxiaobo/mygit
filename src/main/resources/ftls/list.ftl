<!DOCTYPE html>
<html>
<head>
    <title>xxx</title>
    <link rel="stylesheet" href="../css/bootstrap.min14ed.css"
          type="text/css">
    <link rel="stylesheet" href="../css/style.min862f.css" type="text/css">
    <link href="../content/superui/global/css/components.min.css"
          id="style_components" rel="stylesheet" />
    <link rel="stylesheet" href="../plugin/sweetalert/sweetalert.css"
          type="text/css">
    <style>
        .manage_obj {
            width: 326px;
            height: 100px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
        }

        .manage_obj p {
            margin: 0;
        }

        .manage_obj img {
            max-width: 30% !important;
            max-height: 50px !important;
        }
    </style>
</head>
<body style="background-color: white">

<div class="panel panel-default">
    <div class="panel-heading">查询条件</div>
    <div class="panel-body">
        <div class="form-group" style="margin-top:15px">
            <label class="control-label col-sm-1" for="query_title">手动添加你查询条件</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" style="width: 200px;" id="query_title">
            </div>
            <div class="col-sm-1 col-sm-offset-1" style="text-align:left;">
                <button id="toCheck" type="button" style="margin-left:50px" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                </button>
            </div>
            <div class="col-sm-1" style="text-align:left;">
                <a style="margin-left:50px" class="btn btn-default" onclick="reset();"> <span
                        class="glyphicon glyphicon-refresh"></span>&nbsp;清空
                </a>
            </div>
        </div>
    </div>
</div>
<form id="form1" name="form1" action="../${EntityName}/list" method="get" class="form-horizontal">
    <input type="hidden" name="用于接收查询字段值" id="" value=$!pagesModel.req.xxx>
    <div class="page-container">
        <div class="ibox float-e-margins">
            <div class="row">
                <div class="col-sm-12">
                    <a class="btn default" href="../${EntityName}/toInsert"> <span
                            class="glyphicon glyphicon-plus"></span>&nbsp;添加
                    </a> <a class="btn default" onclick="delete${ClassName}Batch()"> <span
                        class="glyphicon glyphicon-remove"> </span>&nbsp;批量删除
                </a>
                </div>
            </div>
            <table class="table table-hover table-striped table-bordered" id="${EntityName}Table">
                <thead>
                <tr>
                    <th width="3%" style="text-align: center;"><input type="checkbox" id="check-all" /></th>
                <#list Properties as x>
                    <th width="5%" style="text-align: center;">xxx</th>
                </#list>
                    <th width="5%" style="text-align: center;">操作</th>
                </tr>
                </thead>
                <tbody>
                #foreach($obj in $pagesModel.results)
                <tr height="45px">
                <#list Properties as x>
                    <#if x.ifPrimaryKey?string("true","false")=="true">
                    <td align="center"><input type="checkbox" class="child-check" value="$!obj.${x.propertyName}"></td>
                    <td align="center">$!obj.${x.propertyName}</td>
                    <#else>
                    <td align="center">$!obj.${x.propertyName}</td>
                    </#if>
                </#list>
                    <td align="center">
                        <a class="btn default" href="../${EntityName}/toUpdate?${PrimaryKey}=${r"${obj."}${PrimaryKey}}">
                            <span class="glyphicon glyphicon-pencil"></span>&nbsp;修改
                        </a>
                        <a class="btn default" onclick="del${ClassName}(${r"${obj."}${PrimaryKey}})">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </a>
                    </td>
                </tr>
                #end
                </tbody>
            </table>
            #parse("htmls/global/listPage.html")
        </div>
    </div>
</form>
<!-- 全局公共类库Begin -->
<script src="../js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../plugin/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
    $("#query_title").val($("#自定义id").val());
    // 全选/反选
    $("#check-all").click(function(){
        $(".child-check").prop("checked",$(this).prop("checked"));
    });
    $(document).on("click",".child-check",function(){
        var flag = $(".child-check").length == $(".child-check:checked").length;
        $("#check-all").prop("checked",flag);
    });
    //清空查询条件
    function reset() {
        $('#query_title').val(null);
    }

    $("#toCheck").click(function(){
        $("#自定义id").val($('#query_title').val());
        $("#form1").submit();
    });

    // 单一删除
    function del${ClassName}(id) {
        swal({
            title : "提示",
            text : "确认操作吗？",
            type : "warning",
            showCancelButton : "true",
            showConfirmButton : "true",
            confirmButtonText : "确定",
            cancelButtonText : "取消",
            animation : "slide-from-top"
        }, function() {
            $.ajax({
                url : "../${EntityName}/delete${ClassName}",
                data : {
                    "id" : id,
                },
                type : "post",
                success : function(data) {
                    if (data.data) {
                        swal("提示", "成功", "success");
                    } else {
                        swal("提示", "失败", "error");
                    }
                    setTimeout("$('#form1').submit();",1000);
                }
            });
        });
    }
    // 批量删除
    function delete${ClassName}Batch(){
        var idList = "";
        $(".child-check:checked").each(function(){
            idList += $(this).val() + "-"
        });
        idList = idList.substring(0,idList.length-1);
        if(idList == ""){
            swal("提示","请选择要删除的选项","warning");
        }else{
            swal({
                title : "提示",
                text : "确认批量操作吗？",
                type : "warning",
                showCancelButton : "true",
                showConfirmButton : "true",
                confirmButtonText : "确定",
                cancelButtonText : "取消",
                animation : "slide-from-top"
            }, function() {
                $.ajax({
                    url : "../${EntityName}/delete${ClassName}Batch",
                    data : {
                        "idList" : idList,
                    },
                    type : "post",
                    success : function(data) {
                        if (data.data) {
                            swal("提示", "成功", "success");
                        } else {
                            swal("提示", "失败", "error");

                        }
                        setTimeout("$('#form1').submit();",1000);
                    }
                });
            });
        }
    }

</script>
</body>
</html>
