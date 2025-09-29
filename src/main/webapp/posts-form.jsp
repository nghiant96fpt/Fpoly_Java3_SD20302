<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
</head>
<body>
	<div class="container">
		<div class="col-6 offset-3">
			<form>
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Tiêu đề</label>
				  <input type="text" class="form-control">
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Nội dung</label>
				  <textarea class="form-control" rows="3"></textarea>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Hình ảnh</label>
				  <input type="file" class="form-control" accept="image/*" multiple>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Danh mục bài viết</label>
				  <select class="form-select" aria-label="Default select example">
					  <option selected>----Chọn danh mục------</option>
					</select>
				</div>
				
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Trạng thái bài viết</label>
				  <div class="form-check">
					  <input class="form-check-input" type="radio" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					   	Hiển thị
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" id="radioDefault2">
					  <label class="form-check-label" for="radioDefault2">
					   	Ẩn
					  </label>
					</div>
				</div>
				
				<button type="submit" class="btn btn-primary">Thêm bài viết</button>
			</form>
		</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>