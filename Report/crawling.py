import os
import sys
import selenium
from time import sleep
from selenium import webdriver
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup as soups


def search_selenium(search_name, search_path):
    search_url = "https://www.google.com/search?q=" + str(search_name) + "&hl=ko&tbm=isch"

    #구글 드라이버에 검색
    browser = webdriver.Chrome('C:\chromedriver.exe')  #크롬 드라이버 경로
    browser.get(search_url) #구글 이미지 검색 url

    last_height = browser.execute_script("return document.body.scrollHeight")

    while True:
        #Scroll down to bottom
        browser.execute_script("window.scrollTo(0, document.body.scrollHeight);")

        sleep(1) #1초 대기
        
        # Calculate new scroll height and compare with last scroll height
        new_height = browser.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            try:
                browser.find_elemen(By.CSS_SELECTOR, ".mye4qd").click()
            except:
                break
        last_height = new_height

    image_count = len(browser.find_elements(By.CSS_SELECTOR, ".rg_i.Q4LuWd"))

    print("로드된 이미지 개수 : ", image_count)

    browser.implicitly_wait(2)  #2초 대기

    search_limit = int(input("원하는 이미지 수집 개수 : "))

    #반복문으로 이미지요소 배열들 들며 작업
    for i in range(search_limit):
        #image = browser.find_element(By.XPATH, "/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div/div[2]/a/img").get_attribute("src")
        image = browser.find_elements(By.CSS_SELECTOR, ".rg_i.Q4LuWd")[i] #작게 뜬 이미지들 모두 선택(elements)
        image.screenshot(search_path + '/' + str(i) + ".jpg")   #저장하고 싶은 경로

    browser.close()

#입력
search_name = input("검색하고 싶은 키워드 : ")
crawling_path = input("저장할 폴더명 입력 : ")
search_path = "./train/" + crawling_path
try:
    # 중복되는 폴더 명이 없다면 생성
    if not os.path.exists(search_path):
        os.makedirs(search_path)
    # 중복된다면 문구 출력 후 프로그램 종료
    else:
        print('이전에 같은 [검색어, 이미지 수]로 다운로드한 폴더가 존재합니다.')
        sys.exit(0)
except OSError:
    print('os error')
    sys.exit(0)

search_selenium(search_name, search_path)
