import nature2 from '../assets/nature2.jpg'

export default function ImageAndFont() {

    return (
        <>
            <h1 className="nanum-pen-script-regular">이미지와 폰트 사용 1</h1>
            <h1 className="SF_HambakSnow">이미지와 폰트 사용 2</h1>
            <img src="/assets/nature1.jpg" />
            <hr />
            <img src={nature2} />

        </>
    )
}