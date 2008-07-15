package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit1 extends expansion_unit {
  public expansion_unit1(local_lookahead local_lookahead, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_lookahead>("local_lookahead", local_lookahead)
    }, firstToken, lastToken);
  }
  public expansion_unit1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit1(cloneProperties(),firstToken,lastToken);
  }
  public local_lookahead getLocal_lookahead() {
    return ((PropertyOne<local_lookahead>)getProperty("local_lookahead")).getValue();
  }
}
