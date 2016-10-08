<header class="top-nav">
    <div class="top-nav-inner">
        <div class="nav-header">
            <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleSM">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a href="home.html" class="brand">
                <!--<i class="fa fa-database"></i>-->
                <span class="brand-name">管理系统</span>
            </a>
        </div>
        <div class="nav-container">
            <button type="button" class="navbar-toggle pull-left sidebar-toggle" id="sidebarToggleLG">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="pull-right m-right-sm">
                <div class="user-block hidden-xs">
                    <a href="#" id="userToggle" data-toggle="dropdown">
                        <!--<img src="images/profile/profile1.jpg" alt="" class="img-circle inline-block user-profile-pic">-->
                        <div class="user-detail inline-block">
                            管理员
                            <i class="fa fa-angle-down"></i>
                        </div>
                    </a>
                    <div class="panel border dropdown-menu user-panel">
                        <div class="panel-body paddingTB-sm">
                            <ul>
                                <li>
                                    <a href="updatePassword?id=1">
                                        <#--<i class="fa fa-power-off fa-lg"></i>-->
                                        <span class="m-left-xs">修改密码</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="logout">
                                        <#--<i class="fa fa-power-off fa-lg"></i>-->
                                        <span class="m-left-xs">登出</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- ./top-nav-inner -->
</header>