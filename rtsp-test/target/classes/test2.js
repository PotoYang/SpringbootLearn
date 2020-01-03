(("undefined" != typeof self ? self : this).webpackJsonp = ("undefined" != typeof self ? self : this).webpackJsonp || []).push([[157], {
    1105: function (e, t, r) {
        "use strict";
        r.d(t, "c", function () {
            return a
        }), r.d(t, "a", function () {
            return c
        }), r.d(t, "d", function () {
            return o
        }), r.d(t, "b", function () {
            return i
        });
        var n = r(10);

        function a(e, t, r) {
            void 0 === r && (r = function (e) {
                return e.modular_pages
            });
            var a = r(e);
            return Object(n.a)(a, ["microsite", t]) || null
        }

        function c(e, t, r) {
            void 0 === r && (r = function (e) {
                return e.modular_pages
            });
            var a = r(e);
            return Object(n.a)(a, ["collection", t]) || null
        }

        function o(e) {
            var t, r;
            e && (t = {predefineLabelIds: Object(n.a)(e, ["header", "center_view", "search_config"], {}).product_labels}, r = Object(n.a)(e, ["meta", "title"]));
            return {searchParams: t, labelSearchRange: r}
        }

        function i(e) {
            if (e && "search" === Object(n.a)(e, ["center_view", "type"], null)) {
                var t = Object(n.a)(e, ["center_view", "search_config"], {}).product_labels;
                if (t && t.length > 0) return "search"
            }
            return "title"
        }
    }, 1203: function (e, t, r) {
        "use strict";
        r.d(t, "b", function () {
            return n
        }), r.d(t, "a", function () {
            return a
        });
        var n = "modular_pages", a = "microsite_extra"
    }, 851: function (e, t, r) {
        "use strict";
        r.r(t);
        var n = r(0), a = r(49), c = r(866), o = r(31), i = r(24), s = r(74), u = r(868), l = r(904), f = r(913),
            h = r(1105), d = r(1203), p = r(902), b = r(192), O = r(51);

        function v() {
            return (v = Object.assign || function (e) {
                for (var t = 1; t < arguments.length; t++) {
                    var r = arguments[t];
                    for (var n in r) Object.prototype.hasOwnProperty.call(r, n) && (e[n] = r[n])
                }
                return e
            }).apply(this, arguments)
        }

        function g() {
            return (g = Object.assign || function (e) {
                for (var t = 1; t < arguments.length; t++) {
                    var r = arguments[t];
                    for (var n in r) Object.prototype.hasOwnProperty.call(r, n) && (e[n] = r[n])
                }
                return e
            }).apply(this, arguments)
        }

        function y(e) {
            if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return e
        }

        var _ = Object(c.a)({
            loader: function () {
                return Promise.all([r.e(0), r.e(1), r.e(2), r.e(3), r.e(41)]).then(r.bind(null, 2968))
            }
        }), E = Object(c.a)({
            loader: function () {
                return Promise.all([r.e(0), r.e(1), r.e(2), r.e(3), r.e(36)]).then(r.bind(null, 2935))
            }
        }), m = Object(c.a)({
            loader: function () {
                return Promise.all([r.e(0), r.e(1), r.e(2), r.e(3), r.e(156)]).then(r.bind(null, 2957))
            }
        }), S = function (e) {
            var t, r;

            function a(t) {
                var r;
                return (r = e.call(this, t) || this).state = {localSearch: Object(u.e)(t.searchParams)}, r.onToggleLocalSearch = r.onToggleLocalSearch.bind(y(y(r))), r
            }

            r = e, (t = a).prototype = Object.create(r.prototype), t.prototype.constructor = t, t.__proto__ = r;
            var c = a.prototype;
            return c.UNSAFE_componentWillMount = function () {
                var e = this.props, t = e.match, r = e.location;
                t.params || r.search || this.props.history.push(Object(s.home)())
            }, c.UNSAFE_componentWillReceiveProps = function (e) {
                Object(i.b)(Object(o.i)(this.props.searchParams)) !== Object(i.b)(Object(o.i)(e.searchParams)) && this.onToggleLocalSearch(Object(u.e)(e.searchParams))
            }, c.onToggleLocalSearch = function (e) {
                this.setState({localSearch: e})
            }, c.render = function () {
                var e = this.props.searchParams, t = this.state.localSearch,
                    r = g({}, this.props, {localSearch: t, onToggleLocalSearch: this.onToggleLocalSearch});
                return e.collection ? n.createElement(_, r) : !e.category || e.thirdCategory || e.keyword || e.hashtag || e.facet ? n.createElement(m, r) : n.createElement(E, r)
            }, a
        }(n.Component);
        t.default = Object(a.c)(function (e, t) {
            var r = t.location, n = t.match, a = e.sharedSearch, c = e.sharedCategory, o = e[d.b],
                s = Object(O.e)(r.pathname), g = Object(u.d)(n.params), y = Object(i.d)(r.search),
                _ = parseInt(y.page, 10) || 0, E = parseInt(g.thirdCategory, 10), m = v({}, g || {}, y),
                S = b.a.formatParamsFromQuery(v({}, m, {
                    page: _,
                    officialMall: s || m.officialMall,
                    keyword: m.keyword || m.hashtag,
                    hashtag: void 0
                })), P = S, T = P.category, R = P.subcategory;
            if (E && T && R) {
                var C = Object(f.c)(c, T, R, E);
                C && (S.keyword = C.display_name)
            }
            var w = !s && Object(l.c)(a.searchPrefill, S);
            return w && Object(p.a)(w.hint, S.keyword) && (S.keyword = void 0, S.searchPrefill = w.id), S.site && (S = v({}, S, Object(h.d)(Object(h.c)({modular_pages: o}, S.site)).searchParams)), {
                isOfficialMallSearch: s,
                searchParams: S,
                seoParams: g,
                searchPrefill: w
            }
        })(S)
    }, 854: function (e, t, r) {
        "use strict";
        r.r(t), function (e) {
            r.d(t, "observe", function () {
                return o
            }), r.d(t, "unobserve", function () {
                return i
            });
            r(591);
            var n = window && window.innerWidth <= 640 ? 80 : 300, a = new WeakMap,
                c = new e.IntersectionObserver(function (e, t) {
                    e.forEach(function (e) {
                        var r = e.target, n = void 0 === e.isIntersecting || e.isIntersecting;
                        if (a.has(r) && n) {
                            var c = a.get(r);
                            c && "function" == typeof c.callback && (c.callback(), c.isMultiple || (t.unobserve(r), a.delete(r)))
                        }
                    })
                }, {rootMargin: "0px -1px " + n + "px -1px"}), o = function (e, t, r) {
                    a.set(e, {isMultiple: !!r, callback: t}), c.observe(e)
                }, i = function (e) {
                    a.delete(e), c.unobserve(e)
                }
        }.call(this, r(93))
    }, 855: function (e, t, r) {
        "use strict";
        (function (e) {
            r.d(t, "a", function () {
                return a
            });
            r(591);
            var n = 300, a = function () {
                function t(t) {
                    var r = this;
                    this.callbackSet = new WeakMap;
                    this.observer = new e.IntersectionObserver(function (e) {
                        e.forEach(function (e) {
                            var t = e.target, n = void 0 === e.isIntersecting || e.isIntersecting;
                            if (r.callbackSet.has(t)) {
                                var a = r.callbackSet.get(t) || {}, c = a.enterViewCallback, o = a.leaveViewCallback,
                                    i = a.intersectionChangeCallback;
                                n ? c && c(e) : o && o(e), i && i(e)
                            }
                        })
                    }, t || {rootMargin: "0px 0px " + n + "px 0px"})
                }

                var r = t.prototype;
                return r.observe = function (e, t, r, n) {
                    e && this.callbackSet && this.observer && (this.callbackSet.set(e, {
                        enterViewCallback: t,
                        leaveViewCallback: r,
                        intersectionChangeCallback: n
                    }), this.observer.observe(e))
                }, r.unobserve = function (e) {
                    e && this.callbackSet && this.observer && (this.callbackSet.delete(e), this.observer.unobserve(e))
                }, t
            }()
        }).call(this, r(93))
    }, 858: function (e, t, r) {
        "use strict";
        var n = r(854);
        r.d(t, "b", function () {
            return n
        });
        var a = r(855);
        r.d(t, "a", function () {
            return a.a
        })
    }, 859: function (e, t, r) {
        "use strict";
        r.d(t, "a", function () {
            return u
        });
        var n = r(862), a = r(140);
        r.d(t, "b", function () {
            return a.c
        });
        var c = r(31), o = r(24), i = r(129), s = (r(402), Object(i.a)());

        function u(e, t) {
            var r = {};
            switch (t = t || Object(c.g)(e), r.search_keyword = e.catalogue || e.keyword || e.hashtag || "", r.page_type = "search", t) {
                case n.c.GLOBAL:
                    r.official_mall = e.officialMall ? "1" : void 0;
                    break;
                case n.c.PREFILL:
                    r.search_prefill = e.searchPrefill;
                    break;
                case n.c.SHOP:
                case n.c.SHOP_CATEGORY:
                    r.page_type = "shop", r.main_catid = e.shop, isNaN(e.shopCollection) || (r.shop_categoryids = e.shopCollection), e.originalCategoryId && (r.shop_categoryids = void 0, r.categoryids = [e.originalCategoryId]);
                    break;
                case n.c.COLLECTION:
                    r.page_type = "collection", r.main_catid = e.collection;
                    break;
                case n.c.CATEGORY:
                    s ? r.fe_categoryids = e.category : r.main_catid = e.category;
                    break;
                case n.c.SUBCATEGORY:
                    s ? r.fe_categoryids = e.subcategory : (r.main_catid = e.category, r.sub_catid = e.subcategory);
                    break;
                case n.c.ATTRIBUTE:
                    r.page_type = "attribute", r.main_catid = e.attrId, r.search_keyword = e.attrVal
            }
            return Object(o.b)(r)
        }
    }, 860: function (e, t, r) {
        "use strict";
        r.d(t, "a", function () {
            return l
        });
        var n = r(0), a = r(141), c = r.n(a), o = r(858), i = r(173);
        var s = o.b.observe, u = o.b.unobserve, l = function (e) {
            var t, r;

            function a(t) {
                return c()(t.onEnterViewPort, "it's compulsory to pass onEnterViewPort function as props to LazyTrigger"), e.call(this, t) || this
            }

            r = e, (t = a).prototype = Object.create(r.prototype), t.prototype.constructor = t, t.__proto__ = r;
            var o = a.prototype;
            return o.UNSAFE_componentWillMount = function () {
                !i.a && this.props.onEnterViewPort()
            }, o.componentDidMount = function () {
                i.a && s(this._ref, this.props.onEnterViewPort, !!this.props.observeMultipleTime)
            }, o.componentWillUnmount = function () {
                i.a && u(this._ref)
            }, o.render = function () {
                var e = this;
                return n.createElement("div", {
                    ref: function (t) {
                        e._ref = t
                    }
                }, this.props.children)
            }, a
        }(n.Component)
    }, 862: function (e, t, r) {
        "use strict";
        r.d(t, "c", function () {
            return n
        }), r.d(t, "a", function () {
            return a
        }), r.d(t, "b", function () {
            return c
        }), r.d(t, "d", function () {
            return o
        });
        r(31), r(401), r(140);
        var n = Object.freeze({
            GLOBAL: "GLOBAL",
            COLLECTION: "COLLECTION",
            SHOP: "SHOP",
            SHOP_CATEGORY: "SHOP_CATEGORY",
            SUBCATEGORY: "SUBCATEGORY",
            CATEGORY: "CATEGORY",
            ATTRIBUTE: "ATTRIBUTE",
            PREFILL: "PREFILL",
            VOUCHER: "VOUCHER",
            SITE: "SITE",
            CATALOGUE: "CATALOGUE"
        }), a = (Object.freeze({
            RELEVANCY: "relevancy",
            TIME: "ctime",
            POPULAR: "pop",
            PRICE: "price",
            DISTANCE: "distance",
            SALES: "sales"
        }), {OTHER: "0", DOMESTIC: "-1", OVERSEAS: "-2"}), c = 4, o = Object.freeze({
            BASIC_SEARCH: 1,
            IN_SHOP_SEARCH: 3,
            IN_OFFICIAL_SHOP_SEARCH: 4,
            CATEGORY: 5,
            SUBCATEGORY: 6,
            THIRD_LEVEL_CATEGORY: 7,
            MICROSITE: 8,
            SEARCH_PREFILL: 9,
            PRODUCT_CATALOGUE: 10,
            VOUCHER: 11
        })
    }, 863: function (e, t, r) {
        "use strict";
        var n = r(10), a = r(31), c = r(859), o = r(401);

        function i() {
            return (i = Object.assign || function (e) {
                for (var t = 1; t < arguments.length; t++) {
                    var r = arguments[t];
                    for (var n in r) Object.prototype.hasOwnProperty.call(r, n) && (e[n] = r[n])
                }
                return e
            }).apply(this, arguments)
        }

        function s(e) {
            var t, r = e.searchParams, s = e.searchStore, u = e.isOfficialMall, l = Object(a.g)(r), f = Object(c.b)(r),
                h = Object(n.a)(s, [f]), d = Object(n.a)(h, ["query_rewrite"], {}),
                p = Object(n.a)(d, ["FEQueryWriteStatus"]), b = Object(n.a)(d, ["rewrite_keyword"], ""),
                O = Object(n.a)(d, ["hint_keywords"], []), v = Object(o.hasSearchFilter)(r, u), g = a.c.NORMAL;
            switch (h && (h.items && 0 === h.items.length || -1 === h.error) && (g = a.c.NO_RESULT), p) {
                case a.c.NO_RESULT:
                    g = a.c.NO_RESULT;
                    break;
                case a.c.NORMAL_WITH_HINT:
                    g = a.c.NORMAL_WITH_HINT;
                    break;
                case a.c.TOO_FEW:
                case a.c.AUTO_CORRECTION:
                case a.c.DROP_WORDS:
                    g = p, t = r, r = i({}, r, {keyword: b});
                    break;
                case a.c.SHOP_INTENTION:
                    g = a.c.SHOP_INTENTION
            }
            return g === a.c.NO_RESULT && (u && r.officialMall && !v ? g = a.c.NO_RESULT_MALL : v || r.officialMall ? g = a.c.NO_RESULT_FILTER : [a.b.SHOP, a.b.SHOP_CATEGORY].includes(l) && (g = a.c.NO_RESULT_SHOP)), {
                resultType: Number(g),
                searchParams: r,
                originalSearchParams: t,
                searchResult: h,
                rewriteKeyword: b,
                hintKeywords: O
            }
        }

        function u(e) {
            var t = e.searchParams, r = e.searchResult, n = e.isOfficialMall, c = Object(o.hasSearchFilter)(t, !!n),
                i = Object(a.g)(t);
            return {
                resultType: n && t.officialMall && !c ? a.c.NO_RESULT_MALL : c || t.officialMall ? a.c.NO_RESULT_FILTER : [a.b.SHOP, a.b.SHOP_CATEGORY].includes(i) ? a.c.NO_RESULT_SHOP : a.c.NO_RESULT,
                searchParams: t,
                searchResult: r
            }
        }

        r.d(t, "a", function () {
            return s
        }), r.d(t, "b", function () {
            return u
        })
    }, 866: function (e, t, r) {
        "use strict";
        r.d(t, "a", function () {
            return u
        });
        var n = r(0), a = r.n(n), c = (r(161), r(50)), o = r(860);

        function i(e) {
            if (void 0 === e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return e
        }

        function s() {
            return a.a.createElement("div", {style: {height: "1px"}})
        }

        function u(e) {
            var t = e.loader, r = e.placeholder, u = e.PlaceholderComponent, l = e.skipLazyTrigger;
            var f = !1, h = Object(n.lazy)(t);
            return function (e) {
                var t, d;

                function p(t) {
                    var r;
                    return (r = e.call(this, t) || this).state = {inViewPort: !1}, r.onEnterViewPort = r.onEnterViewPort.bind(i(i(r))), r
                }

                d = e, (t = p).prototype = Object.create(d.prototype), t.prototype.constructor = t, t.__proto__ = d;
                var b = p.prototype;
                return b.onEnterViewPort = function () {
                    f || (f = !0), !this.state.inViewPort && this.setState({inViewPort: !0})
                }, b.render = function () {
                    var e = this, t = this.props, i = r || (u ? a.a.createElement(u, t) : a.a.createElement(s, null));
                    return a.a.createElement(c.e.Consumer, null, function (r) {
                        var c = r.history, s = f && "POP" === c.action || l;
                        return e.state.inViewPort || s ? a.a.createElement(n.Suspense, {fallback: i}, a.a.createElement(h, t)) : a.a.createElement(o.a, {onEnterViewPort: e.onEnterViewPort}, i)
                    })
                }, p
            }(n.PureComponent)
        }
    }, 868: function (e, t, r) {
        "use strict";
        r.d(t, "a", function () {
            return i
        }), r.d(t, "d", function () {
            return s
        }), r.d(t, "e", function () {
            return u
        }), r.d(t, "c", function () {
            return l
        }), r.d(t, "b", function () {
            return f
        });
        var n = r(142), a = r(31), c = r(863);

        function o() {
            return (o = Object.assign || function (e) {
                for (var t = 1; t < arguments.length; t++) {
                    var r = arguments[t];
                    for (var n in r) Object.prototype.hasOwnProperty.call(r, n) && (e[n] = r[n])
                }
                return e
            }).apply(this, arguments)
        }

        function i(e, t, r, n) {
            void 0 === n && (n = !1);
            var c = {};
            return n && (c.officialMall = !0), r ? (e && (c.keyword = e), t && (c = o({}, Object(a.i)({
                searchPrefill: t.searchPrefill,
                category: t.category,
                subcategory: t.subcategory,
                shop: t.shop,
                site: t.site,
                collection: t.collection,
                promotionId: t && t.promotionId
            }), c))) : e && (e = e.trim().replace(/\s+/g, " "), c.keyword = e), c
        }

        function s(e) {
            var t = e.l3CategoryId, r = e.subCategoryId, n = e.categoryId, a = e.hashtag, c = e.collectionId,
                o = e.categoryName, i = {};
            return a && (i.keyword = "#" + a.replace(/-/g, " ")), n && (i.category = "" + n, i.categoryName = o), r && (i.subcategory = "" + r, i.categoryName = o), t && (i.thirdCategory = "" + t, i.keyword = o), c && (i.collection = "" + c), i
        }

        function u(e) {
            return !(!e || !(e.category || e.shop || e.collection || e.promotionId || e.searchPrefill || e.site))
        }

        function l(e, t) {
            return Math.min(Math.ceil((t && (t.adjust && t.adjust.count || t.total_count) || 0) / e), n.e)
        }

        function f(e, t, r) {
            void 0 === r && (r = !1);
            var n = e, a = n.searchParams, o = n.searchResult, i = l(t, o);
            return o && (a.page || 0) >= i && (e = Object(c.b)({
                searchResult: o,
                searchParams: a,
                isOfficialMall: r
            })), e
        }
    }, 902: function (e, t, r) {
        "use strict";
        var n = r(403);
        r.d(t, "a", function () {
            return n.a
        }), r.d(t, "b", function () {
            return n.b
        }), r.d(t, "c", function () {
            return n.c
        })
    }, 904: function (e, t, r) {
        "use strict";
        var n = r(343);
        r.d(t, "a", function () {
            return n.getCurrentSearchPrefill
        }), r.d(t, "b", function () {
            return n.getPrefillById
        }), r.d(t, "c", function () {
            return n.getPrefillBySearchParams
        })
    }, 913: function (e, t, r) {
        "use strict";
        r.d(t, "a", function () {
            return a
        }), r.d(t, "b", function () {
            return c
        }), r.d(t, "c", function () {
            return o
        }), r.d(t, "d", function () {
            return i
        }), r.d(t, "e", function () {
            return s
        });
        var n = r(10);

        function a(e, t) {
            return e ? e.data.find(function (e) {
                return e.main.catid === t
            }) : null
        }

        function c(e, t) {
            var r = Object(n.a)(e, ["data"]);
            if (!Array.isArray(r)) return null;
            var a = null;
            return r.find(function (e) {
                var r = e.sub;
                if (!r) return !1;
                var n = r.find(function (e) {
                    return e.catid === t
                });
                return !!n && (a = n, !0)
            }), a
        }

        function o(e, t, r, n) {
            var c = e.categoryTree;
            if (!c || !t) return null;
            var o = a(c, t);
            if (!o) return null;
            if (!r) return (o || {}).main;
            var i = (o.sub || []).find(function (e) {
                return e.catid === r
            });
            return i ? n ? (i.sub_sub || []).find(function (e) {
                return e.catid === n
            }) : i : null
        }

        function i(e) {
            return Object(n.a)(e, ["categoryList", "category_list"], [])
        }

        function s(e) {
            return Object(n.a)(e, ["categoryTree", "data"], [])
        }
    }
}]);
//# sourceMappingURL=https://shopee.sg/assets/SearchPageContent.4377cc276c8f74e9e377.js.map