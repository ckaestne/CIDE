package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class relational_expressionInternal2 extends relational_expressionInternal {
  public relational_expressionInternal2(relational_operator2I relational_operator2I, type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<relational_operator2I>("relational_operator2I", relational_operator2I),
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public relational_expressionInternal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new relational_expressionInternal2(cloneProperties(),firstToken,lastToken);
  }
  public relational_operator2I getRelational_operator2I() {
    return ((PropertyOne<relational_operator2I>)getProperty("relational_operator2I")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}
