# Android MVP Architecture

### A basic sample android application to understand MVVM in a very simple way. Just clone, build, run and understand MVVM.

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **ui**: View classes along with their corresponding Presenters.
4. **utils**: Utility classes.

#### Key points
* Using base abstract classes to support MVP methodology while
  avoiding Activity leaks by releasing the view when it's no longer needed 
  ,BasePresenter repsonsable to destroy the view onActivityDestory().
* BaseActivity by default initialize the Presenter & DataManager.

#### Library
* LiveData
* ViewModel

--------------------------------------------------------------------------------------------

**Happy Coding!!!** ![](https://i.imgur.com/rneCZCN.png)

--------------------------------------------------------------------------------------------

### License
```
~ MIT
```
