/* eslint-disable no-unused-vars */
import { useState, useEffect, useContext } from "react";
import "./Chart.css";
import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
import * as am5 from "@amcharts/amcharts5";
import * as am5xy from "@amcharts/amcharts5/xy";
import * as am5stock from "@amcharts/amcharts5/stock";
import GameContext from "../../context/GameContext";

function Chart(props) {
  const [dayCount, setDayCount] = useState(365);
  const { curPriceNum, setCurPriceNum } = useContext(GameContext);

  useEffect(() => {
    /* Chart code */
    // Create root element
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
    let root = am5.Root.new("chartdiv");

    // Set themes
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/concepts/themes/
    root.setThemes([am5themes_Animated.new(root)]);

    // Create a stock chart
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock-chart/#Instantiating_the_chart
    let stockChart = root.container.children.push(
      am5stock.StockChart.new(root, {})
    );

    // Set global number format
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/concepts/formatters/formatting-numbers/
    root.numberFormatter.set("numberFormat", "#,###.00");

    // Create a main stock panel (chart)
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock-chart/#Adding_panels
    let mainPanel = stockChart.panels.push(
      am5stock.StockPanel.new(root, {
        wheelY: "zoomX",
        panX: true,
        panY: true,
      })
    );

    // Create value axis
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
    let valueAxis = mainPanel.yAxes.push(
      am5xy.ValueAxis.new(root, {
        renderer: am5xy.AxisRendererY.new(root, {
          pan: "zoom",
        }),
        extraMin: 0.1, // adds some space for for main series
        tooltip: am5.Tooltip.new(root, {}),
        numberFormat: "#,###.00",
        extraTooltipPrecision: 2,
      })
    );

    let dateAxis = mainPanel.xAxes.push(
      am5xy.GaplessDateAxis.new(root, {
        baseInterval: {
          timeUnit: "day",
          count: 1,
        },
        renderer: am5xy.AxisRendererX.new(root, {}),
        tooltip: am5.Tooltip.new(root, {}),
      })
    );

    // Add series
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
    let valueSeries = mainPanel.series.push(
      am5xy.CandlestickSeries.new(root, {
        name: "MSFT",
        clustered: false,
        valueXField: "Date",
        valueYField: "Close",
        highValueYField: "High",
        lowValueYField: "Low",
        openValueYField: "Open",
        calculateAggregates: true,
        xAxis: dateAxis,
        yAxis: valueAxis,
        legendValueText:
          "open: [bold]{openValueY}[/] high: [bold]{highValueY}[/] low: [bold]{lowValueY}[/] close: [bold]{valueY}[/]",
        legendRangeValueText: "",
      })
    );

    // Set main value series
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock-chart/#Setting_main_series
    stockChart.set("stockSeries", valueSeries);

    // Add a stock legend
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock-chart/stock-legend/
    let valueLegend = mainPanel.plotContainer.children.push(
      am5stock.StockLegend.new(root, {
        stockChart: stockChart,
      })
    );

    // Create volume axis
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
    let volumeAxisRenderer = am5xy.AxisRendererY.new(root, {
      inside: true,
    });

    volumeAxisRenderer.labels.template.set("forceHidden", true);
    volumeAxisRenderer.grid.template.set("forceHidden", true);

    let volumeValueAxis = mainPanel.yAxes.push(
      am5xy.ValueAxis.new(root, {
        numberFormat: "#.#a",
        height: am5.percent(20),
        y: am5.percent(100),
        centerY: am5.percent(100),
        renderer: volumeAxisRenderer,
      })
    );

    // Add series
    // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
    let volumeSeries = mainPanel.series.push(
      am5xy.ColumnSeries.new(root, {
        name: "Volume",
        clustered: false,
        valueXField: "Date",
        valueYField: "Volume",
        xAxis: dateAxis,
        yAxis: volumeValueAxis,
        legendValueText: "[bold]{valueY.formatNumber('#,###.0a')}[/]",
      })
    );

    // color columns by stock rules
    valueSeries.columns.template.adapters.add(
      "stroke",
      function (stroke, target) {
        const dataItem = target.dataItem;
        if (dataItem) {
          if (dataItem.get("valueY") > dataItem.get("openValueY")) {
            return am5.color("#FF0000"); // 상승 캔들에 대한 빨간색
          } else {
            return am5.color("#0000FF"); // 하락 캔들에 대한 파란색
          }
        }
        return stroke;
      }
    );

    // Set main series
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock-chart/#Setting_main_series
    stockChart.set("volumeSeries", volumeSeries);
    valueLegend.data.setAll([valueSeries, volumeSeries]);

    // Add cursor(s)
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
    mainPanel.set(
      "cursor",
      am5xy.XYCursor.new(root, {
        yAxis: valueAxis,
        xAxis: dateAxis,
        snapToSeries: [valueSeries],
        snapToSeriesBy: "y!",
      })
    );

    // Add scrollbar
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
    let scrollbar = mainPanel.set(
      "scrollbarX",
      am5xy.XYChartScrollbar.new(root, {
        orientation: "horizontal",
        height: 50,
      })
    );
    stockChart.toolsContainer.children.push(scrollbar);

    let sbDateAxis = scrollbar.chart.xAxes.push(
      am5xy.GaplessDateAxis.new(root, {
        baseInterval: {
          timeUnit: "day",
          count: 1,
        },
        renderer: am5xy.AxisRendererX.new(root, {}),
      })
    );

    let sbValueAxis = scrollbar.chart.yAxes.push(
      am5xy.ValueAxis.new(root, {
        renderer: am5xy.AxisRendererY.new(root, {}),
      })
    );

    let sbSeries = scrollbar.chart.series.push(
      am5xy.LineSeries.new(root, {
        valueYField: "Close",
        valueXField: "Date",
        xAxis: sbDateAxis,
        yAxis: sbValueAxis,
      })
    );

    sbSeries.fills.template.setAll({
      visible: true,
      fillOpacity: 0.3,
    });

    // Set up series type switcher
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock/toolbar/series-type-control/
    let seriesSwitcher = am5stock.SeriesTypeControl.new(root, {
      stockChart: stockChart,
    });

    seriesSwitcher.events.on("selected", function (ev) {
      setSeriesType(ev.item.id);
    });

    function getNewSettings(series) {
      let newSettings = [];
      am5.array.each(
        [
          "name",
          "valueYField",
          "highValueYField",
          "lowValueYField",
          "openValueYField",
          "calculateAggregates",
          "valueXField",
          "xAxis",
          "yAxis",
          "legendValueText",
          "stroke",
          "fill",
        ],
        function (setting) {
          newSettings[setting] = series.get(setting);
        }
      );
      return newSettings;
    }

    function setSeriesType(seriesType) {
      // Get current series and its settings
      let currentSeries = stockChart.get("stockSeries");
      let newSettings = getNewSettings(currentSeries);

      // Remove previous series
      let data = currentSeries.data.values;
      mainPanel.series.removeValue(currentSeries);

      // Create new series
      let series;
      // eslint-disable-next-line default-case
      switch (seriesType) {
        case "line":
          series = mainPanel.series.push(
            am5xy.LineSeries.new(root, newSettings)
          );
          break;
        case "candlestick":
        case "procandlestick":
          newSettings.clustered = false;
          series = mainPanel.series.push(
            am5xy.CandlestickSeries.new(root, newSettings)
          );
          if (seriesType === "procandlestick") {
            series.columns.template.get("themeTags").push("pro");
          }
          break;
        case "ohlc":
          newSettings.clustered = false;
          series = mainPanel.series.push(
            am5xy.OHLCSeries.new(root, newSettings)
          );
          break;
      }

      // Set new series as stockSeries
      if (series) {
        valueLegend.data.removeValue(currentSeries);
        series.data.setAll(data);
        stockChart.set("stockSeries", series);
        let cursor = mainPanel.get("cursor");
        if (cursor) {
          cursor.set("snapToSeries", [series]);
        }
        valueLegend.data.insertIndex(0, series);
      }
    }

    // Stock toolbar
    // -------------------------------------------------------------------------------
    // https://www.amcharts.com/docs/v5/charts/stock/toolbar/

    // 추가
    let periodSelector = am5stock.PeriodSelector.new(root, {
      stockChart: stockChart,
      periods: [
        { timeUnit: "day", count: 1, name: "1D" },
        { timeUnit: "month", count: 1, name: "1M" },
        { timeUnit: "month", count: 3, name: "3M" },
        { timeUnit: "month", count: 6, name: "6M" },
        { timeUnit: "year", count: 1, name: "1Y" },
        { timeUnit: "max", name: "Max" },
      ],
    });

    valueSeries.events.once("datavalidated", function () {
      periodSelector.selectPeriod({ timeUnit: "month", count: 6 });
    });

    let toolbar = am5stock.StockToolbar.new(root, {
      container: document.getElementById("chartcontrols"),
      stockChart: stockChart,
      controls: [
        am5stock.IndicatorControl.new(root, {
          stockChart: stockChart,
          legend: valueLegend,
        }),
        am5stock.DateRangeSelector.new(root, {
          stockChart: stockChart,
        }),
        periodSelector,
        seriesSwitcher,
        am5stock.DrawingControl.new(root, {
          stockChart: stockChart,
        }),
        am5stock.ResetControl.new(root, {
          stockChart: stockChart,
        }),
        am5stock.SettingsControl.new(root, {
          stockChart: stockChart,
        }),
      ],
    });

    let tooltip = am5.Tooltip.new(root, {
      getStrokeFromSprite: false,
      getFillFromSprite: false,
    });

    tooltip.get("background").setAll({
      strokeOpacity: 1,
      stroke: am5.color(0x000000),
      fillOpacity: 1,
      fill: am5.color(0xffffff),
    });

    function makeEvent(date, letter, color, description) {
      let dataItem = dateAxis.createAxisRange(
        dateAxis.makeDataItem({ value: date })
      );
      let grid = dataItem.get("grid");
      if (grid) {
        grid.setAll({
          visible: true,
          strokeOpacity: 0.2,
          strokeDasharray: [3, 3],
        });
      }

      let bullet = am5.Container.new(root, {
        dy: -100,
      });

      let circle = bullet.children.push(
        am5.Circle.new(root, {
          radius: 10,
          stroke: color,
          fill: am5.color(0xffffff),
          tooltipText: description,
          tooltip: tooltip,
          tooltipY: 0,
        })
      );

      let label = bullet.children.push(
        am5.Label.new(root, {
          text: letter,
          centerX: am5.p50,
          centerY: am5.p50,
        })
      );

      dataItem.set(
        "bullet",
        am5xy.AxisBullet.new(root, {
          location: 0,
          stacked: true,
          sprite: bullet,
        })
      );
    }

    makeEvent(1619006400000, "S", am5.color(0xff0000), "Split 4:1");
    makeEvent(1619006400000, "D", am5.color(0x0000ff), "Dividends paid");
    makeEvent(1634212800000, "D", am5.color(0x0000ff), "Dividends paid");

    // set data to all series
    // valueSeries.data.setAll(data);
    // volumeSeries.data.setAll(data);
    // sbSeries.data.setAll(data);

    let rawData = props.data || [];
    let formattedData = rawData.map((item) => ({
      Date: item.date * 1000,
      Open: item.openPrice,
      High: item.highestPrice,
      Low: item.lowestPrice,
      Close: item.closingPrice,
      Volume: item.volumes,
    }));

    // console.log(formattedData[0].Date);
    // console.log(formattedData[0].Open);
    // console.log(formattedData[0].High);
    // console.log(formattedData[0].Low);
    // console.log(formattedData[0].Close);
    // console.log(formattedData[0].Volume);

    // 변환된 데이터로 차트 설정
    setDayCount(365 + props.thisTurn);
    valueSeries.data.setAll(formattedData.slice(0, dayCount));
    volumeSeries.data.setAll(formattedData.slice(0, dayCount));
    sbSeries.data.setAll(formattedData.slice(0, dayCount));

    valueSeries.columns.template.adapters.add("fill", function (fill, target) {
      const dataItem = target.dataItem;
      if (dataItem) {
        if (dataItem.get("valueY") > dataItem.get("openValueY")) {
          return am5.color("#FF0000"); // 상승 캔들에 대한 빨간색
        } else {
          return am5.color("#0000FF"); // 하락 캔들에 대한 파란색
        }
      }
      return fill;
    });

    // 현재가 update
    setCurPriceNum(formattedData[dayCount - 1].Close);
    return () => {
      root.dispose();
    };
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [props.thisTurn]);

  return (
    <>
      <div id="chartcontrols"></div>
      <div id="chartdiv"></div>
    </>
  );
}

export default Chart;
