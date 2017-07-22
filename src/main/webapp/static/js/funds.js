function onLoad() {
    // create funds table
    var table = createTable();
    $(".get-funds").click(function() {
        var url = getServer() + "api/funds";
        // var url = "http://localhost:8080/api";
        $.get(url, function(res) {
            if (res.action === "ok") {
                table.setFunds(res.funds);
            }
        }.bind(this));
    });
    $(window).click(function() {
        $(".menu").hide();
    });

    // create fund dialog
    var dialog = createDialog();
    $(".new-fund").click(function() {
        dialog.newFund();
    });
    dialog.$on("save", function(fund) {
        if (fund.id) {
            // edit fund
            $.ajax({
                type: "put",
                url: getServer() + "api/fund",
                data: fund,
                dataType: "json",
                success: function(res) {
                    if (res.action === "ok") {
                        table.editFund(fund);
                    }
                }
            });
        } else {
            // new fund
            var url = getServer() + "api/fund";
            $.post(url, fund, function(res) {
                if (res.action === "ok") {
                    fund.id = res.id;
                    table.addFunds(fund);
                }
            });
        }
    });

    // create menu
    var menu = createMenu();
    menu.$on("editFund", function() {
        var fid = $(".funds tr.active .fid").text();
        var url = getServer() + "api/fund/" + fid;
        $.get(url, function(res) {
            if (res.action === "ok") {
                dialog.editFund(res.fund);
            }
        });

    });
    menu.$on("deleteFund", function() {
        var fid = $(".funds tr.active .fid").text();
        $.ajax({
            type: "delete",
            url: getServer() + "api/fund/" + fid,
            dataType: "json",
            success: function(res) {
                if (res.action === "ok") {
                    table.deleteFund(fid);
                }
            }
        });
    });
}

var getServer = function() {
    return $(".server input").val();
}

var createTable = function() {
    var funds = new Vue({
        el: ".funds",
        data: {
            funds: []
            
    
        },
        methods: {
            setFunds: function(funds) {
                this._resetRowNum(funds);
                this.funds = funds;
            },
            addFunds: function(fund) {
                this.funds.push(fund);
                this._resetRowNum(this.funds);
            },
            editFund: function(fund) {
                for (var i = 0; i < this.funds.length; i++) {
                    if (this.funds[i].id === fund.id) {
                        $.extend(this.funds[i], fund);
                        break;
                    }
                }
            },
            deleteFund: function(fundId) {
                for (var i = 0; i < this.funds.length; i++) {
                    if (this.funds[i].id === fundId) {
                        this.funds.splice(i, 1);
                        break;
                    }
                }
                this._resetRowNum(this.funds);
            },
            _resetRowNum: function(funds) {
                for (var i =0; i < funds.length; i++) {
                    funds[i].row = i + 1;
                }
            },
            selectRow: function(evt) {
                var element = evt.target;
                while (element && element.tagName !== "TR") {
                    element = element.parentElement;
                }

                if (element) {
                    $(this.$el).find("tr").removeClass("active");
                    var $element = $(element);
                    $element.addClass("active");
                    $(".menu").css({
                        display: "block",
                        left: evt.clientX,
                        top: evt.clientY
                    });
                    evt.stopPropagation();
                }
            }
        }
    });

    return funds;
};

var createDialog = function() {
    var dialog = new Vue({
        el: ".dialog",
        data: {
            fund: {}
        },
        methods: {
            ok: function(evt) {
                if (!this.fund.name) {
                    alert("Fund name is required, please enter name.");
                    return;
                }
                this.$emit("save", $.extend({}, this.fund));
                this._hide();
            },
            cancel: function(evt) {
                this._hide();
            },
            _show: function() {
                $(this.$el).css("display", "flex");
            },
            _hide: function() {
                $(this.$el).hide();
            },
            newFund: function() {
                this.fund = {};
                this._show();
            },
            editFund: function(fund) {
                this.fund = fund;
                this._show();
            }
        }
    });

    return dialog;
};

var createMenu = function() {
    var menu = new Vue({
        el: ".menu",
        methods: {
            editFund: function(evt) {
                this.$emit("editFund");
                $(this.$el).hide();
            },
            deleteFund: function(evt) {
                this.$emit("deleteFund");
                $(this.$el).hide();
            }
        }
    });

    return menu;
};