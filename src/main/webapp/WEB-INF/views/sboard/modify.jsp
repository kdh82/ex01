<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
#modifyDivImg {
	position: relative;
	float: left;
}

#btnDelImg {
	position: absolute;
	top: 10px;
	right: 10px;
}
</style>
<%@ include file="../include/header.jsp"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<!-- BOX-header  -->

				<form role="form" method="post" action="modify">
					<div class="box-body">
						<input type="hidden" name="page" value=${cri.page }> <input
							type="hidden" name="perPageNum" value=${cri.perPageNum }>
						<input type="hidden" name="searchType" value=${cri.searchType }>
						<input type="hidden" name="keyword" value=${cri.keyword }>
						<input type="hidden" name="bno" value="${board.bno }"> <input
							type="hidden" name="viewcnt" value="${board.viewcnt }"> <input
							type="hidden" name="sRegdate" value="${board.regdate }">
						<div class="form-group">
							<label>Title</label> <input type="text" name="title"
								class="form-control" placeholder="Enter Title"
								value="${board.title }">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="" name="content" class="form-control"
								placeholder="Enter Content">${board.content }</textarea>

						</div>
						<div class="form-group">
							<label>Writer</label> <input type="text" name="writer"
								class="form-control" placeholder="Enter Writer"
								value="${board.writer }">
						</div>
						<div class="form-group" id="imgFiles" style="height: 200px;">
							<c:forEach var="item" items="${board.files }">
								<div id="modifyDivImg"
									style="width: 150px; height: 150px;">
									<span><img src="displayFile?filename=${item }"
										style="max-width: 100%; min-width: 100%; min-height: 100%; max-height: 100%;">
									<button id="btnDelImg" data-src="${item }">X</button></span>
								</div>
							</c:forEach>

						</div>
					</div>
					<!-- BOX BODY -->
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Modify</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
$(function(){
			$(document).on("click", "button#btnDelImg", function(){
				event.preventDefault();
				var item = $(this).attr("data-src");
			var inputObj =  "<input type='hidden' name='filename' value='"+item+"'>";
				$("#btnDelImg").append(inputObj);
			$(this).parent().hide();
			
			})
	
})
</script>
<%@ include file="../include/footer.jsp"%>
