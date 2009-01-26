package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt9 extends small_stmt {
  public small_stmt9(assert_stmt assert_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<assert_stmt>("assert_stmt", assert_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt9(cloneProperties(),firstToken,lastToken);
  }
  public assert_stmt getAssert_stmt() {
    return ((PropertyOne<assert_stmt>)getProperty("assert_stmt")).getValue();
  }
}
