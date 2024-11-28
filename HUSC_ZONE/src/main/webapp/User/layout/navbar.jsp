<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg bg-primary-custom py-2">
        <div class="container-fluid mx-5">
            <a class="navbar-brand text-white fw-bold" href="#">
			    <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" style="height: 40px; width: 130px;" class="d-inline-block align-top">
			</a>
            <div class="collapse navbar-collapse">
                <!-- Form tìm kiếm -->
                <form class="d-flex mx-auto align-items-center">
                    <select class="form-select form-select-sm me-2" style="margin-left: 150px;">
                        <option value="0">-- Chọn ngành học --</option>
                        <option value="cntt">Công nghệ thông tin</option>
                        <option value="yte">Y tế</option>
                        <option value="xaydung">Xây dựng</option>
                        <option value="khac">Khác</option>
                    </select>
    
                    <select class="form-select form-select-sm me-2">
                        <option value="0">-- Chọn dạng tài liệu --</option>
                        <option value="slide">Slide bài giảng</option>
                        <option value="baitap">Bài tập</option>
                        <option value="doan">Đồ án</option>
                        <option value="khac">Khác</option>
                    </select>

                    <input class="form-control form-control-sm me-2" 
                           style="width: 200px;"  
                           placeholder="Tìm kiếm tài liệu..." 
                           autofocus>
    
                    <button type="submit" class="btn btn-primary-custom btn-sm">
                        <i class="bi bi-search"></i>
                    </button>
                </form>
    
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link text-white" href="/show-docs.html">Tài liệu</a></li>
                    <li class="nav-item"><a class="nav-link text-white" href="/show-status.html">Bài đăng</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                            Thông báo <span class="badge bg-danger">3</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><h6 class="dropdown-header">Thông báo mới</h6></li>
                            <li><a class="dropdown-item" href="#">Bài chia sẻ đã được phê duyệt</a></li>
                            <li><a class="dropdown-item" href="#">Bình luận mới trên tài liệu</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><h6 class="dropdown-header">Thông báo cũ</h6></li>
                            <li><a class="dropdown-item" href="#">Tài liệu được xếp hạng cao</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="" alt="" class="rounded-circle" width="30" height="30"> 
                            Hi, Nhu Y
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/my-profile.html">Trang cá nhân</a></li>
                            <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>