package com.meretskiy.game;

import com.badlogic.gdx.Game;
import com.meretskiy.game.screen.MenuScreen;

/**
 * Основной класс приложения
 *
 *  SpriteBatch batch; //батчер - отвечает за отрисовку объектов, формирует список на отрисовку и передает его в графический процессор
 *     Texture img; //текстура - объект хранит вс себе текстуру которую вытаскиваем из папки assets по названию
 *     Texture background;
 *     TextureRegion region; //позволяет вырезать кусок текстуры по задданным параметрам
 *
 *   public void create() { //инициализация объектов
 *         batch = new SpriteBatch();
 *         img = new Texture("badlogic.jpg");
 *         background = new Texture("сosmos.jpeg");
 *         region = new TextureRegion(img, 25, 25, 100, 100); //не хранит текстуру, а только ссылку на нее
 *
 *         //Операции над векторами
 *         //сложение векторов
 *         Vector2 v1 = new Vector2(2, 2);
 *         Vector2 v2 = new Vector2();
 *         v2.set(2, 1);
 *         v1.add(v2);
 *         System.out.println("v1.add(v2) v1.x = " + v1.x + " v1.y = " + v1.y);
 *
 *         //вычитание векторов
 *         v1.set(3, 7);
 *         v2.set(6, 2);
 * 		v1.sub(v2);
 * 		System.out.println("v1.sub(v2) v1.x = " + v1.x + " v1.y = " + v1.y);
 *
 * 		//вычисление длины вектора
 *         System.out.println("v1.len() = " + v1.len());
 *
 *         //умножение вектора на скаляр
 *         v1.setLength(100); //задаем длинну вектора
 *         v1.scl(0.95f); // уменьшаем длинну вектора на 5%
 *         System.out.println("v1.len() = " + v1.len());
 *
 *         //нормализация вектора - получение вектора длинной 1
 *         v1.nor();
 *         System.out.println("v1.len() = " + v1.len());
 *
 *         //копирование вектора
 *         Vector2 v3 = v1.add(v2); // в результате получим v3, но и значение v1 тоже поменяется
 *
 *         v3 = v1.cpy().add(v2); //копируем значение v1 для рассчета, сам v1 не меняется (не использовать в render() - забьем память)
 *         System.out.println("v1.x = " + v1.x + " v1.y = " + v1.y);
 *         System.out.println("v3.x = " + v3.x + " v3.y = " + v3.y);
 *
 *         //скалярное произведение векторов (позволяет определить угол между векторами)
 *         v1.set(1,1);
 *         v2.set(-1,1);
 *         v1.nor();
 *         v2.nor();
 *         //выведем угол между векторами в радианах (acos скалярного произведения данных векторов)
 *         System.out.println(Math.acos(v1.dot(v2)));

 *
 *     public void render() { //отрисовка и вся логика, срабатывает 60 раз в секунду
 *         ScreenUtils.clear(Color.BLACK); //очистка экрана или в RGB или через Color.CLEAR
 *         batch.begin(); // начало списка на отрисовку
 *         batch.draw(background, 0, 0);
 *         batch.setColor(1, 1, 1, 0.5f); //устанавливаем цвет и прозрачность для последующих отрисовок, если указать еще одну, то она применится к последующим
 *         batch.draw(img, x, y, width, height); //рисуем
 *         batch.draw(region, 300, 300); //какая отрисовка последняя такая картинка сверху и будет
 *         batch.end(); //конец списка на отрисовку
 *         x++;
 *         y++;
 *     }
 *
 *     public void dispose() { //выгрузка объектов из ппамяти по завершению работы
 *         batch.dispose();
 *         img.dispose();
 *         background.dispose();
 *     }
 *
 */
public class MyStarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this)); //устанавливаем в качестве текущего экрана
    }
}
