'use strict';
$(document).ready(() => {
    $('#kakaoApiTable').DataTable({
                  processing: false,
                  serverSide: true,
                  ordering: false,
                  searching: false,
                  lengthChange: false,
                  info: false,
                  ajax: function (data, callback, settings) {
                      let page = Math.floor(data.start / data.length) + 1;
                      let keyword = $('#keyword').val();
                      if(keyword){
                        mySpringApi.findAll({page: page, keyword: keyword, pageLength: data.length},
                          function (message, response) {
                              if (message != null) {
                                  alert(message);
                                  return false;
                              }
                              callback({
                                  recordsTotal: response.meta.total_count,
                                  data: response.documents
                              });
                          });
                      }else{
                        return false
                      }
                  },
                  columns: [
                      {data: "place_name"},
                      {data: "address_name"},
                      {data: "phone"}
                  ]
              });



    $('#kakaoApiTable tbody').on('click', 'td', function () {
      var tableRow = $('#kakaoApiTable').DataTable().row(this).data();
      document.getElementById("tableRowId").innerHTML = '장소 : '+tableRow.place_name;
      document.getElementById("tableRowPhone").innerHTML = 'Phone : '+tableRow.phone;
      document.getElementById("roadAddressName").innerHTML = '도로명 : '+tableRow.road_address_name;
      document.getElementById("categoryName").innerHTML = '카테고리 : '+tableRow.category_name;
      document.getElementById("tableRowUrl").innerHTML = '<a href="https://map.kakao.com/link/map/'+tableRow.id +'">[지도보기]</a>';
      $('#myModal').show();
    });

    $('#kakaoApiForm').submit((e) => {
        e.preventDefault();
        if (!confirm("검색 하시겠습니까?")) {
            return false;
        }
        $('#kakaoApiTable').DataTable().ajax.reload(null, false);
    });

    $('#myHistoryInfo').on('click', function(event) {
      event.preventDefault(); // To prevent following the link (optional)
      $('#myHistoryList').empty();
      const suffix = 'history';
      let history;
      mySpringApi.myHistory(suffix,
          function (message, response) {
              if (message != null) {
                  alert(message);
                  return false;
              }
              history = response;
              if(history.length === 0) {
                $('#myHistoryList').append('<li>검색한 키워드가 없습니다.</li>');
              }
              $.each(history, function(i, item){
                $('#myHistoryList').append('<li>'+item.keyword+'</li>');
              })
              $('#myHistoryModal').show();
          });
    });
    $('#mostKeywordsInfo').on('click', function(event) {
      event.preventDefault(); // To prevent following the link (optional)
      $('#mostKeywordsList').empty();
      let keywords;
      mySpringApi.mostKeywords(
          function (message, response) {
              if (message != null) {
                  alert(message);
                  return false;
              }
              keywords = response;
              if(keywords.length === 0) {
                $('#mostKeywordsList').append('<li>검색한 키워드가 없습니다.</li>');
              }
              $.each(keywords, function(i, item){
                $('#mostKeywordsList').append('<li>'+item+'</li>');
              })
              $('#mostKeywordsModal').show();
          });
    });
});