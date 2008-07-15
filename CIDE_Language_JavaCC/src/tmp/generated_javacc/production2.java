package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class production2 extends production {
  public production2(regular_expr_production regular_expr_production, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<regular_expr_production>("regular_expr_production", regular_expr_production)
    }, firstToken, lastToken);
  }
  public production2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new production2(cloneProperties(),firstToken,lastToken);
  }
  public regular_expr_production getRegular_expr_production() {
    return ((PropertyOne<regular_expr_production>)getProperty("regular_expr_production")).getValue();
  }
}
