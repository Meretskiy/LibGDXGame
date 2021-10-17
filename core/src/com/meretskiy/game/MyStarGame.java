package com.meretskiy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyStarGame extends ApplicationAdapter {
	SpriteBatch batch; //батчер - отвечает за отрисовку объектов, формирует список на отрисовку и передает его в графический процессор
	Texture img; //текстура - объект хранит вс себе текстуру которую вытаскиваем из папки assets по названию
	Texture background;
	TextureRegion region; //позволяет вырезать кусок текстуры по задданным параметрам
	int x;
	int y;
	int width = 200;
	int height = 200;
	
	@Override
	public void create () { //инициализация объектов
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("сosmos.jpeg");
		region = new TextureRegion(img, 25,25,100,100); //не хранит текстуру, а только ссылку на нее
	}

	@Override
	public void render () { //отрисовка и вся логика, срабатывает 60 раз в секунду
		ScreenUtils.clear(Color.BLACK); //очистка экрана или в RGB или через Color.CLEAR
		batch.begin(); // начало списка на отрисовку
		batch.draw(background, 0, 0);
		batch.setColor(1,1,1,0.5f); //устанавливаем цвет и прозрачность для последующих отрисовок, если указать еще одну, то она применится к последующим
		batch.draw(img, x, y, width, height); //рисуем
		batch.draw(region, 300, 300); //какая отрисовка последняя такая картинка сверху и будет
		batch.end(); //конец списка на отрисовку
		x++;
		y++;
	}
	
	@Override
	public void dispose () { //выгрузка объектов из ппамяти по завершению работы
		batch.dispose();
		img.dispose();
	}
}
