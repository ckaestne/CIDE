package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class not_test1 extends not_test {
  public not_test1(not_test not_test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<not_test>("not_test", not_test)
    }, firstToken, lastToken);
  }
  public not_test1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new not_test1(cloneProperties(),firstToken,lastToken);
  }
  public not_test getNot_test() {
    return ((PropertyOne<not_test>)getProperty("not_test")).getValue();
  }
}
