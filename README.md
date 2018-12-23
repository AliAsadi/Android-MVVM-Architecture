# Android MVP Architecture

### A basic sample android application to understand MVP in a very simple way. Just clone, build, run and understand MVP.

<img src=https://i.imgur.com/FPlzDXn.jpg >

# Project Structure

<img src=https://i.imgur.com/mYuhRLT.jpg >

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
* RoomDatabase
* ButterKnife
* Retrofit
* Gson
* Glide

--------------------------------------------------------------------------------------------

**Feel free to submit any type of issues and suggestions for improving the coding standard**

**Happy Coding!!!** ![](https://i.imgur.com/rneCZCN.png)

--------------------------------------------------------------------------------------------

### License
```
   Copyright (C) 2018 Ali Esa Assadi
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to Android MVP Architecture
Just make pull request. You are in!
