package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeEnd3 extends typeEnd {
  public typeEnd3(operator_declaration operator_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<operator_declaration>("operator_declaration", operator_declaration)
    }, firstToken, lastToken);
  }
  public typeEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeEnd3(cloneProperties(),firstToken,lastToken);
  }
  public operator_declaration getOperator_declaration() {
    return ((PropertyOne<operator_declaration>)getProperty("operator_declaration")).getValue();
  }
}
