<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">BOARD LIST ALL(${login })</h3>
				</div>
				<!-- BOX-header  -->

				<div class="box-body">
					<select name="searchType">
						<option value="n" ${cri.searchType=='n'? 'selected' :'' }>---</option>
						<option value="t" ${cri.searchType=='t'? 'selected' :'' }>Title</option>
						<option value="c" ${cri.searchType=='c'? 'selected' :'' }>Content</option>
						<option value="w" ${cri.searchType=='w'? 'selected' :'' }>Writer</option>
						<option value="tc" ${cri.searchType=='tc'? 'selected' :'' }>Title OR Content</option>
						<option value="cw" ${cri.searchType=='cw'? 'selected' :'' }>Content OR Writer</option>
						<option value="tcw" ${cri.searchType=='tcw'? 'selected' :'' }>Title OR Content OR Writer</option>
					</select>
					
					<input type="text" name="keyword" value="${cri.keyword}">
					<button id="searchBtn">Search</button>
				</div>

				<div class="box-body">
					<table class="table table-boarded">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>

						<c:forEach var="board" items="${list }">
							<tr>
								<td>${board.bno }</td>
								<td><a href="read${pageMaker.makeSearch(cri.page) }&bno=${board.bno }&fromlist=true">${board.title}<span style="color:black;font-weight:bold;">[${board.replycnt}]</span></a></td>
								<td>${board.writer }</td>
								<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><span class="badge bg-red">${board.viewcnt }</span></td>
							</tr>

						</c:forEach>
					</table>
					<button class="btn btn-primary" onclick="btnNew()">write</button>
					<!-- <form role="form" method="post" action="listAll">
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">write</button>
						</div>
					</form> -->
				</div>

				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<!-- 이전 페이지 있을때만 표시 -->
								<li><a href="listPage${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
							</c:if>
							
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li ${pageMaker.cri.page == idx? 'class=active' : '' } >
									<a href="listPage${pageMaker.makeSearch(idx) }">${idx }</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next}">
								<!-- 이후 페이지 있을때만 표시 -->
								<li><a href="listPage${pageMaker.makeSearch(pageMaker.endPage+1) }">&raquo;</a></li>
							</c:if>

						</ul>
					</div>
				</div>

			</div>
		</div>
	</div>
</section>

<script>
	$(function() {
		var result= '${msg}';
		if(result=='SUCCESS'){
			alert("처리가 완료되었습니다.");
		}
		
		$("#searchBtn").click(function() {
			var keyword=$("input[name='keyword']").val();
			var searchType=$("select[name='searchType']").val();
			location.href="listPage?searchType="+searchType+"&keyword="+keyword;
		})
	})	

	function btnNew() {
		location.href = "register";
	}
</script>

<%@ include file="../include/footer.jsp"%>
