package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class foreach_statement extends GenASTNode {
  public foreach_statement(type type, identifier identifier, expression expression, embedded_statement embedded_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<expression>("expression", expression),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement)
    }, firstToken, lastToken);
  }
  public foreach_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new foreach_statement(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
}
