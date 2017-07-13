<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#imgFiles div{
	width: 150px;
	height: 150px;
	float: left;
}
#imgFiles img{
	float: left;
	min-width: 100%;
	max-width: 100%;
	min-height: 100%;
	max-height: 100%;
}	
</style>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<!-- BOX-header  -->
				
				<div class ="box-body">
					<div class="form-group">
						<label>Title</label>
						<input readonly="readonly" type="text" name="title" class="form-control"
							value="${board.title }">
					</div>
					
					<div class="form-group">
						<label>Content</label>
						<textarea readonly="readonly" type="text" name="content" class="form-control"
							>${board.content }</textarea>
					</div>
					
					<div class="form-group">
						<label>Writer</label>
						<input readonly="readonly" type="text" name="writer" class="form-control"
							value="${board.writer }">
							
					</div>
					<div class="form-group" id="imgFiles" style="width:900px;height:200px;">
						
								<c:forEach var="item" items="${board.files }">
									<div>
									<img src="displayFile?filename=${item }">
									</div>		
								</c:forEach>	
							
							</div>
				</div>
				<!-- BOX-BODY  -->
				
				<div class="box-footer">
					<button class="btn btn-warning">수정하기</button>
					<button class="btn btn-danger">삭제하기</button>
					<button class="btn btn-primary" id="btnBack">돌아가기</button>
				</div>
				
				<form id="f1" role="form" method="post">
					<input type="hidden" name="bno" value=${board.bno }>
					<input type="hidden" name="page" value=${cri.page }>
					<input type="hidden" name="perPageNum" value=${cri.perPageNum }>
					<input type="hidden" name="searchType" value=${cri.searchType }>
					<input type="hidden" name="keyword" value=${cri.keyword }>
				</form>
			</div>
		</div>
	</div> <!-- 게시물 detail -->
	
<script> /* 게시물 script */
	$(function() {
		$(".btn-danger").click(function() {
			$("#f1").attr("action", "delete"); //post 형식의 delete command 호출됨
			$("#f1").submit();
		});
		
		$(".btn-warning").click(function() {
			$("#f1").attr("action", "modify");
			$("#f1").attr("method", "get"); 	// get 형식의 modify command 호출됨
			$("#f1").submit();
		});
		
		$("#btnBack").click(function() {
			//location.href="listPage?page=${cri.page }";
			$("#f1").attr("action", "listPage");
			$("#f1").attr("method", "get"); 
			$("#f1").submit();
		});
	});
</script>
	
	<div class="row">
		<div class="col-md-12">
			<div class="box box-succeess">
				<div class="box-header">
					<h3 class="box-title">댓글 추가</h3>
				</div>
				<div class="box-body">
					<label for="newReplyWriter">작성자</label>
					<input type="text" placeholder="User ID" id="newReplyWriter" class="form-control">
					<label for="newReplyText">댓글 내용</label>
					<input type="text" placeholder="Reply Text" id="newReplyText" class="form-control">
				</div>
				<div class="box-footer">
					<button class="btn btn-primary" id="btnAdd">Add Reply</button>
				</div>
			</div><!-- add reply -->
			<ul class="pagination"></ul>
			<ul class="timeline">
				<li class="time-label" id="repliesList">
					<span class="bg-green" id="btnList">Replies List(<span class="replycntClass">${board.replycnt}</span>)</span>
				</li>
			</ul>
			<ul class="pagination"></ul>
		</div>
	</div> <!-- 댓글 -->
	
	<div class="modal fade" id="modfyModal" role="dialog">
		<div class="modal-dialog modal-lg">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Modal Header</h4>
	        </div>
	        <div class="modal-body">
	         	<label for="newReplyWriter">작성자</label> 
				<input type="text" placeholder="User ID" id="newReplyWriter2" class="form-control">
				<label for="newReplyText">댓글 내용</label> 
				<input type="text" placeholder="Reply Text" id="newReplyText2" class="form-control">
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-info" id="replyModBtn" data-dismiss="modal">수정</button>
	        </div>
	      </div>
	    </div>
	</div> <!--  Modal 창 -->
	
	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<script id="temp" type="text/x-handlevars-template">
		{{#each.}}
			<li class="replyLi" data-rno={{rno}}>
				<i class="fa fa-comments bg-blue"></i>
				<div class="timeline-item">
					<span class="time">
						<i class="fa fa-clock-o"></i>{{tempdate regdate}}
					</span>
					<h3 class="timeline-header"><strong>{{rno}}</strong> -<span class="r_writer">{{replyer}}</span></h3>
					<div class="timeline-body">{{replytext}} </div>
					<div class="timeline-footer">
			  	     	<a class="btn btn-primary btn-xs btn_r_modify" data-toggle="modal" data-target="#modfyModal" data-rno={{rno}}>Modify</a>
						<a class="btn btn-primary btn-xs btn_r_delete" data-toggle="modal" data-rno={{rno}}>Delete</a>
					</div>			
				</div>
			</li>
		{{/each}}
	</script>
	
	<script id="temp2" type="text/x-handlevars-template">
		{{#list}}
			<li class="replyLi" data-rno={{rno}}>
				<i class="fa fa-comments bg-blue"></i>
				<div class="timeline-item">
					<span class="time">
						<i class="fa fa-clock-o"></i>{{tempdate regdate}}
					</span>
					<h3 class="timeline-header"><strong>{{rno}}</strong> -<span class="r_writer">{{replyer}}</span></h3>
					<div class="timeline-body">{{replytext}} </div>
					<div class="timeline-footer">
			  	     	<a class="btn btn-primary btn-xs btn_r_modify" data-toggle="modal" data-target="#modfyModal" data-rno={{rno}}>Modify</a>
						<a class="btn btn-primary btn-xs btn_r_delete" data-toggle="modal" data-rno={{rno}}>Delete</a>
					</div>			
				</div>
			</li>
		{{/list}}
	</script>
	
	
	
	
	
	<script type="text/javascript">
	var curPage = 1;
		Handlebars.registerHelper("tempdate", function(time) {
			var dateObj = new Date(time);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth()+1;
			var date = dateObj.getDate();
			
			return year + "/" + month + "/" + date;
			
		});
		
		var bno = ${board.bno };
		function getAllList() {
			$.ajax({
				//pageContext.getRequest().getContextPath(); 
				// '/'를 포함한 프로젝트 이름을 반환한다  : '/ex01'
				url: "${pageContext.request.contextPath}/replies/all/"+bno+"/"+page,
				type : "get",
				dataType: "json",
				success:function(data){
					console.log(data);
					var source = $("#temp").html();
					var template = Handlebars.compile(source);
					$(".replyLi").remove();
					$(".timeline").append(template(data));
				}
					
			})
		} // 페이징 없는 전체 리스트 가져오기
		
		
		function getPageList(page) {
			$.ajax({
				url: "${pageContext.request.contextPath}/replies/all/"+bno+"/"+page,
				type : "get",
				dataType: "json",
				success:function(data){
					console.log(data);
					var source = $("#temp2").html();
					var template = Handlebars.compile(source);
					$(".replyLi").remove();
					$(".timeline").append(template(data));
					$(".replycntClass").html(data.pageMaker.totalCount);
					var str2 = "";
					for(var i = data.pageMaker.startPage; i < data.pageMaker.endPage+1; i++){
						if(i == page){
							//str2 += "<a href='"+i+"' style='font-size:20px; font-weight:bold'>"+i+"</a>     ";
							str2 += "<li class='active'><a href='"+i+"'>"+i+"</a></li>";
						}else{
							str2 += "<li><a href='"+i+"'>"+i+"</a></li>";
						}
					}
					$(".pagination").html(str2);
				}
					
			})
		} // 페이징한 리스트 가져오기
		
		$(document).on("click", ".pagination a", function(e) {
			e.preventDefault();
			curPage = $(this).attr("href");
			
			getPageList(curPage);
		}); // 페이지 클릭시 이동
		
		$("#btnList").click(function() {
			// 위에서 부터 읽어내려오므로.... html보다 script 하단이면 레디 태그 설정 필요 없다..
			//getAllList();
			getPageList(curPage);
		}) // list get
		
		$("#btnAdd").click(function() {
			var writer = $("#newReplyWriter").val();
			var text = $("#newReplyText").val();
			var sendData = {
					bno:bno,
					replytext:text,
					replyer:writer
			};
			
			$.ajax({
				url : "${pageContext.request.contextPath}/replies/add",
				type:"post",
				dataType:"text",
				data: JSON.stringify(sendData),
				headers :{"Content-Type":"application/json"},
				success: function(data) {
					console.log(data);
					getPageList(curPage);
				}
			})
		})	// reply add
		
		
		
		$(document).on("click", ".btn_r_modify", function(event) {
			event.preventDefault(); // a tag 전송 막기
			var rno = $(this).attr("data-rno");
			console.log(rno +"번차례");
			$("h4.modal-title").html(rno);
			$(".modal-body").find("#newReplyWriter2").val($(this).parents("li.replyLi").find(".r_writer").html());
			$(".modal-body").find("#newReplyText2").val($(this).parents("li.replyLi").find(".timeline-body").html());
		})
			$("#replyModBtn").click(function() {
				var rno = $("h4.modal-title").html();
				var newReplyWriter2 = $("#newReplyWriter2").val();
				var newReplyText2 = $("#newReplyText2").val();
				var sendData ={
						rno:rno,
						replytext: newReplyText2,
						replyer :newReplyWriter2
				};
				$.ajax({
					url : "${pageContext.request.contextPath}/replies/"+rno,
					type : "put",
					dataType : "text",
					data: JSON.stringify(sendData),
					headers :{"Content-Type":"application/json"},
					success: function(data) {
						console.log(rno + "수정하기 " +data);
						if(data =="SUCCESS"){
							// 수정이 누적됩니다??? 왜죠????
							alert(rno+"번 댓글 수정완료");
							//getAllList();
							getPageList(curPage);
						}
					}
				})
			})
	
		
		$(document).on("click", ".btn_r_delete", function(event) {
			event.preventDefault(); // a tag 전송 막기
			var rno = $(this).attr("data-rno");
			if(confirm("정말로 삭제하시겠습니까?")){
				//글 지우기	
				$.ajax({
					url : "${pageContext.request.contextPath}/replies/"+rno,
					type : "delete",
					dataType : "text",
					data: "text",
					success: function(data) {
						console.log(rno + "삭제하기 " +data);
						if(data =="SUCCESS"){
							alert(rno+"번 댓글 삭제완료");
							//getAllList();
							getPageList(curPage);
						}
					}
				})
			}
		})
		
	</script>

	
</section>


<%@ include file="../include/footer.jsp" %>    