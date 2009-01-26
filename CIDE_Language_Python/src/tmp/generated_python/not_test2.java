package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class not_test2 extends not_test {
  public not_test2(comparison comparison, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<comparison>("comparison", comparison)
    }, firstToken, lastToken);
  }
  public not_test2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new not_test2(cloneProperties(),firstToken,lastToken);
  }
  public comparison getComparison() {
    return ((PropertyOne<comparison>)getProperty("comparison")).getValue();
  }
}
