public class Test {
  private void foo(){
if (SPLConfiguration.FEATURE2) {
    foo();
}
if (SPLConfiguration.FEATURE1) {
    _i1=3;
}
    _j2=5;
if (SPLConfiguration.FEATURE1) {
    _j2=_j2 + _i1;
}
if (SPLConfiguration.FEATURE2) {
    foo();
}
  }
  int _i1;
  int _j2;
}
