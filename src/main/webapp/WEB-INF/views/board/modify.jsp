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
				
				<form role="form" method="post" action="modify">
					<div class="box-body">
							<input type="hidden" name="page" value="${cri.page }">
							<input type="hidden" name="bno" value="${board.bno }">
							<input type="hidden" name="viewcnt" value="${board.viewcnt }">
							<input type="hidden" name="sRegdate" value="${board.regdate }">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${board.title }">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="" name="content" class="form-control" 
							placeholder="Enter Content">${board.content }</textarea>
							
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${board.writer }">
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

<%@ include file="../include/footer.jsp" %>      