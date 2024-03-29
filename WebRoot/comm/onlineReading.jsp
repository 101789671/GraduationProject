<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/common.jsp"%>
<!doctype html>
<html>
<head>
    <title>FlexPaper</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
    <style type="text/css" media="screen">
        html, body	{ height:100%; }
        body { margin:0; padding:0; overflow:auto; }
        #flashContent { display:none; }
    </style>

    <link rel="stylesheet" type="text/css" href="<%=path %>/css/flexpaper.css" />
    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/flexpaper.js"></script>
    <script type="text/javascript" src="<%=path %>/js/flexpaper_handlers.js"></script>
</head>
<body>
<div id="documentViewer" class="flexpaper_viewer" style="width:100%;height:100%"></div>

<script type="text/javascript" charset="utf-8">

var swfPath;
   <c:forEach items="${gpFileList}" var="item" varStatus="status" >
	var myclass = new Array();
	swfPath = "${item.fileName}";
	</c:forEach>
console.log("swfPath:",swfPath);//控制台打印
    var startDocument = "Paper";
    $('#documentViewer').FlexPaperViewer(
            { config : {
                SWFFile : '../docs/'+swfPath,

                Scale : 1,
                ZoomTransition : 'easeOut',
                ZoomTime : 0.5,
                ZoomInterval : 0.2,
                FitPageOnLoad : true,
                FitWidthOnLoad : false,
                FullScreenAsMaxWindow : false,
                ProgressiveLoading : false,
                MinZoomSize : 0.2,
                MaxZoomSize : 5,
                SearchMatchAll : true,
                InitViewMode : 'Portrait',
                RenderingOrder : 'flash',
                StartAtPage : '',

                ViewModeToolsVisible : true,
                ZoomToolsVisible : true,
                NavToolsVisible : true,
                CursorToolsVisible : true,
                SearchToolsVisible : true,
                WMode : 'window',
                localeChain: 'en_US'
            }}
    );
    function getDocumentUrl(document){
        return "php/services/view.php?doc={doc}&format={format}&page={page}".replace("{doc}",document);
    }


</script>

<script type="text/javascript">
    var url = window.location.href.toString();

    if(location.length==0){
        url = document.URL.toString();
    }

    if(url.indexOf("file:")>=0){
      console.log("有文件");//控制台打印
        jQuery('#documentViewer').html("<div style='position:relative;background-color:#ffffff;width:420px;font-family:Verdana;font-size:10pt;left:22%;top:20%;padding: 10px 10px 10px 10px;border-style:solid;border-width:5px;'><img src='http://flexpaper.devaldi.com/resources/warning_icon.gif'>&nbsp;<b>You are trying to use FlexPaper from a local directory.</b><br/><br/> FlexPaper needs to be copied to a web server before the viewer can display its document properlty.<br/><br/>Please copy the FlexPaper files to a web server and access the viewer through a http:// url.</div>");
    }else {
		  console.log("没有文件");//控制台打印
	}
</script>
</body>
</html>
