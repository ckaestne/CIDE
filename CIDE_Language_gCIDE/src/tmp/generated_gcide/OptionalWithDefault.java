package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionalWithDefault extends GenASTNode {
  public OptionalWithDefault(Unit unit, ASTStringNode string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Unit>("unit", unit),
      new PropertyOne<ASTStringNode>("string_literal", string_literal)
    }, firstToken, lastToken);
  }
  public OptionalWithDefault(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionalWithDefault(cloneProperties(),firstToken,lastToken);
  }
  public Unit getUnit() {
    return ((PropertyOne<Unit>)getProperty("unit")).getValue();
  }
  public ASTStringNode getString_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("string_literal")).getValue();
  }
}
