function Product() {

  const $categoryTab = document.getElementsByClassName(
      'event_tab_lst tab_lst_min')[0];

  const $leftItems = document.getElementsByClassName(
      'left_items')[0];

  const $rightItems = document.getElementsByClassName(
      'right_items')[0];

  const setProducts = (products) => {
    const items = products.items;
    let itemCount = 0;

    while (itemCount < 4) {
      const $item = document.createElement('li');
      const $itemBook = document.createElement('a');
      const $itemPreview = document.createElement('div');
      const $imageThumbnail = document.createElement('img');
      const $imageBorder = document.createElement('span');
      const $eventText = document.createElement('div');
      const $eventTextTitle = document.createElement('h4');
      const $title = document.createElement('span');
      const $smallTitle = document.createElement('small');
      const $eventTextDescription = document.createElement('p');

      $item.setAttribute("class", "item");
      $itemBook.setAttribute("href", "detail");
      $itemBook.setAttribute("class", "item_book");

      $itemPreview.setAttribute("class", "item_preview");
      $imageThumbnail.setAttribute("alt",
          items[itemCount].productDescription);
      $imageThumbnail.setAttribute("class", "img_thumb");
      $imageThumbnail.setAttribute("src",
          "/img/" + items[itemCount].productImageUrl);
      $imageBorder.setAttribute("class", "img_border");

      $eventText.setAttribute("class", "event_txt");
      $eventTextTitle.setAttribute("class", "event_txt_tit");
      $smallTitle.setAttribute("class", "sm");
      $title.innerHTML = items[itemCount].productDescription;
      $smallTitle.innerHTML = items[itemCount].placeName;
      $eventTextDescription.setAttribute("class", "event_txt_dsc");
      $eventTextDescription.innerHTML = items[itemCount].productContent;

      $itemPreview.appendChild($imageThumbnail);
      $itemPreview.appendChild($imageBorder);
      $itemBook.appendChild($itemPreview);
      $eventTextTitle.appendChild($title);
      $eventTextTitle.appendChild($smallTitle);
      $eventText.appendChild($eventTextTitle);
      $eventText.appendChild($eventTextDescription);
      $itemBook.appendChild($itemPreview);
      $itemBook.appendChild($eventText);
      $item.appendChild($itemBook);

      if (itemCount < 2) {
        $leftItems.appendChild($item);
      } else {
        $rightItems.appendChild($item);
      }
      itemCount++;
    }

    const $countElement = document.getElementsByClassName('pink')[0];
    $countElement.innerHTML = String(products.totalCount) + "개";
  }

  const clearProducts = () => {
    while ($leftItems.hasChildNodes()) {
      $leftItems.removeChild($leftItems.firstChild);
    }
    while ($rightItems.hasChildNodes()) {
      $rightItems.removeChild($rightItems.firstChild);
    }
  }

  const loadProducts = (categoryId, start) => {
    let request = new XMLHttpRequest();
    request.open('GET', '/api/products'
        + '?categoryId=' + String(categoryId)
        + '&start=' + String(start));
    request.send();

    request.onreadystatechange = function () {
      if (request.readyState == 4 && request.status == 200) {
        const products = JSON.parse(this.responseText);
        if (start === 0) {
          clearProducts();
        }
        setProducts(products);
      }
    };
  }

  const clickCategory = (event) => {
    const $target = event.target;
    if ($target.classList.contains('category')
        || $target.classList.contains('anchor')) {
      const $targetAnchor = $target.closest('.item');
      const categoryId = $targetAnchor.getAttribute('data-category');
      loadProducts(categoryId, 0);
    } else {
      alert($target.className + " <<<");
    }
  }

  this.init = () => {
    loadProducts(0, 0);
    $categoryTab.addEventListener('click', clickCategory);
  }
}

const product = new Product();
product.init();
