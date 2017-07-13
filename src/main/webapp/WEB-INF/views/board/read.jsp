<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

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
				</div>
				<!-- BOX-BODY  -->
				
				<div class="box-footer">
					<button class="btn btn-warning">수정하기</button>
					<button class="btn btn-danger">삭제하기</button>
					<button class="btn btn-primary">돌아가기</button>
				</div>
				
				<form id="f1" role="form" method="post">
					<input type="hidden" name="bno" value=${board.bno }>
					<input type="hidden" name="page" value=${cri.page }>
				</form>
			</div>
		</div>
	</div>
	
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
					<button class="btn btn-primary">Add Reply</button>
				</div>
			</div><!-- add reply -->
			<div class="timeline">
				<button class="btn btn-warning">Replies List</button>
			</div>
		</div>
	</div>
	
	
</section>

<script>
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
		
		$(".btn-primary").click(function() {
			//location.href="listPage?page=${cri.page }";
			$("#f1").attr("action", "listPage");
			$("#f1").attr("method", "get"); 
			$("#f1").submit();
		});
	});
</script>

<%@ include file="../include/footer.jsp" %>    