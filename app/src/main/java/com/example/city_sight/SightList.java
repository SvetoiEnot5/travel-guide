package com.example.city_sight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;

public class SightList extends AppCompatActivity{

    ImageButton pointerSettings;

    Point point1 = new Point(44.618353, 33.524238);
    Point point2 = new Point(44.611255, 33.492841);
    Point point3 = new Point(44.612060, 33.493052);
    Point point4 = new Point(44.602585, 33.456460);
    Point point5 = new Point(44.595371, 33.523104);
    Sight sight1 = new Sight("Памятник затопленным кораблям", "Монумент в Севастополе, архитектурный символ города, установлен вблизи Приморского бульвара рядом с площадью Нахимова.Создан по проекту скульптора академика А. Г. Адамсона, архитектора В. А. Фельдмана и военного инженера Ф. О. Энберга. Силуэт памятника используется в качестве эмблемы Севастополя. Высота монумента - 16,7 м.\n" +
            "Дата постройки:1905 г.", point1);
    Sight sight2 = new Sight("Херсонес", "Мыс на западе Гераклейского полуострова, крайняя западная точка Севастополя и южной части Крымского полуострова. Вдаётся в Чёрное море. На мысе с 1816 года действует маяк, обеспечивающий подход кораблей и судов в порт Севастополь, высота маяка 36 метров. ", point2,"09:00-20:00");
    Sight sight3 = new Sight("Свято-Владимирский собор в Херсонесе", "Православный храм в Севастополе, на территории музея-заповедника «Херсонес Таврический». Относится к Севастопольскому благочинию Симферопольской и Крымской епархии Русской православной церкви. Построен по проекту Давида Гримма в месте, которое считается одним из тех, где в 987 году был крещён великий князь Владимир. Освящён в 1891 году; восстановлен из полуразрушенного состояния в 1990-х - начале 2000-х годов.\n" +
            "Дата постройки:17 октября 1891 г..", point3);
    Sight sight4 = new Sight("Парк Победы",
            "Парк в Севастополе, посвящённый Победе в Великой Отечественной войне и заложенный в год 30-летия Победы в 1975 году. Парк располагается в Гагаринском районе города, между Круглой и Стрелецкой бухтами. Общая площадь его территории сегодня составляет порядка 50 га. Парк Победы пользуется репутацией популярного места отдыха. Главной достопримечательностью украшением служит памятник Георгию Победоносцу, возведённый к 220-летию Севастополя в 2003 году. В парке имеется несколько достопримечательностей, развлекательных комплексов, пляж.", point4);
    Sight sight5 = new Sight("Панорама Оборона Севастополя",
            "Музей-панорама, посвящённый первой обороне Севастополя." +
            "Дата основания:1904 г..", point5);
    String[] titleList = {
            sight1.getTitle(), sight2.getTitle(), sight3.getTitle(), sight4.getTitle(), sight5.getTitle()
    };
    Sight[] sights = {
            sight1, sight2, sight3, sight4, sight5
    };

    User user = new User();
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("3e9ed211-3558-476a-ab52-9b29735e3a9e");
        setContentView(R.layout.activity_sight_list);

        Bundle arguments = getIntent().getExtras();
        user.setName(arguments.getString("name"));
        user.setSurname(arguments.getString("surname"));
        user.setEmail(arguments.getString("email"));

        textView = (TextView) findViewById(R.id.helloUser);
        textView.setText("Здравствуйте, ".concat(user.getName()).concat("!"));

        ListView sightList = (ListView) findViewById(R.id.sightList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, titleList);
        sightList.setAdapter(adapter);
        sightList.setOnItemClickListener((parent, view, position, id) -> mapAct(sights[position]));

        pointerSettings = findViewById(R.id.imageButtonPointerSettings);
        pointerSettings.setOnClickListener(v -> onMapSettingsClick());
    }

    private void onMapSettingsClick(  ) {
        Intent intent = new Intent(this, PointerSettingsActivity.class);
        startActivity(intent);
    }

    public void mapAct(Sight sight) {
        Intent intent = new Intent(this, SightMap.class);
        intent.putExtra("title", sight.getTitle());
        intent.putExtra("fullDisc", sight.getFullDisc());
        intent.putExtra("workHours", sight.getWorkHours());
        intent.putExtra("latitude", sight.getCoordinates().getLatitude());
        intent.putExtra("longitude", sight.getCoordinates().getLongitude());
        startActivity(intent);
    }
}