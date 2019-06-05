<!DOCTYPE html>
<html>
<head>
    <title>添加xxx</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/bootstrap.min14ed.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.min93e3.css" type="text/css">
    <link rel="stylesheet" href="../plugin/iCheck/custom.css" type="text/css">
    <link rel="stylesheet" href="../css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="../css/style.min862f.css" type="text/css">
    <link rel="stylesheet" href="../css/xcConfirm.css" type="text/css">
    <link rel="stylesheet" href="../css/pikaday.css"/>
    <link rel="stylesheet" type="text/css" href="../js/dist/css/wangEditor.min.css">
    <!-- 全局主题样式 -->
    <link href="../content/superui/global/css/components.min.css" id="style_components" rel="stylesheet" />
    <link href="../content/superui/base/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" />
    <link href="../content/superui/base/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" />
    <!-- jstree -->
    <link href="../content/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" />
    <link href="../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <!--sweetalert样式-->
    <link rel="stylesheet" href="../plugin/sweetalert/sweetalert.css" type="text/css"></link>
    <link rel="stylesheet" href="../css/plugins/chosen/chosen.css" type="text/css"></link>
</head>
<body style="background-color:white">
<form id="add${ClassName}Form" name="add${ClassName}Form" method="post" action="#" class="form-horizontal m-t">
    <div class="panel panel-default col-sm-offset-1" style="width: 80%;">
        <div class="panel-heading">
            <h4 class="modal-title"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加xxx</h4>
        </div>
        <div class="panel-body" style="text-align: center;">
            <#list Properties as x>
                <#if x.ifPrimaryKey?string("true","false")!="true">
                 <div class="form-group">
                     <label class="col-sm-offset-2 col-sm-1 control-label">自行定义：</label>
                     <div class="col-sm-4" style="text-align:left;">
                         <input id="${x.propertyName}" name="${x.propertyName}" value="" class="form-control"type="text" style="width:400px;display:inline-block;">
                     </div>
                 </div>
                </#if>
            </#list>

            <div class="form-group">
                <label class="col-sm-offset-2 col-sm-1 control-label">（自行定义）图片：</label>
                <div class="col-sm-8" style="text-align:left;">
                    <input type="hidden" name="自行定义(与属性字段名相同)" id="自行定义(与属性字段名相同)">
                    <a class="btn btn-info" id="prodImgBtn">上传图片</a> <br><br>
                    <div id="prodImgDiv"></div>
                </div>
            </div>
        <div class="panel-footer" style="text-align: center;">
            <a style="width: 100px;"  class="btn default" onclick="formSubmit()"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;确定</a>
            <a style="width: 100px;"  class="btn default" href="#" onclick ="javascript:history.go(-1);"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;返回</a>
        </div>
    </div>
</form>
<script src="../content/superui/min/js/superui.common.min.js"></script>
<script src="../content/superui/base/bootstrap-modal/js/bootstrap-modal.js"></script>
<script src="../content/superui/base/bootstrap-modal/js/bootstrap-modalmanager.js"></script>
<script src="../js/jquery.min.js?v=2.1.4"></script>
<script src="../js/bootstrap.min.js?v=3.3.6"></script>
<script src="../js/plugins/peity/jquery.peity.min.js"></script>
<script src="../js/content.min.js?v=1.0.0"></script>
<script src="../js/plugins/iCheck/icheck.min.js"></script>
<script src="../js/demo/peity-demo.min.js"></script>
<script src="../js/plugins/chosen/chosen.jquery.js"></script>
<script src="../content/plugins/jstree/dist/jstree.min.js"></script>
<!-- sweetalert脚本 -->
<script type="text/javascript" src="../plugin/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="../plugin/qiniuyun/qiNiuYunUpload.js"></script>
<script type="text/javascript" src="../js/dist/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/dist/js/wangEditor.min.js"></script>
<!-- 富文本编辑器七妞云上传 -->
<script type="text/javascript" src="../js/wangEditorQiniuyun.js"></script>
<script type="text/javascript">
    //生成编辑器
    var editor = new wangEditor('填写想要生成编辑器的id');
    editor.config.customUpload = true;  // 设置自定义上传的开关
    editor.config.customUploadInit = uploadInit;  // 配置自定义上传初始化事件，uploadInit方法在上面定义了
    editor.create();

    $(function(){
        uploadImgforNum('prodImgBtn','img','prodImgDiv',1);
    });

    //提交信息
    function formSubmit(){
        var ${EntityName}Info = $('#add${ClassName}Form').serialize();
        $.ajax({
            url:"../${EntityName}/insert${ClassName}",
            data : ${EntityName}Info,
            type : "post",
            success : function(res){
                if (res.data) {
                    swal("提示", "成功", "success");
                }else{
                    swal("提示", "失败", "error");
                }
            }
        });
    }
</script>
</body>
</html>
