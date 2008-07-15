package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class using_statement extends GenASTNode {
  public using_statement(resource_acquisition resource_acquisition, embedded_statement embedded_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<resource_acquisition>("resource_acquisition", resource_acquisition),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement)
    }, firstToken, lastToken);
  }
  public using_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new using_statement(cloneProperties(),firstToken,lastToken);
  }
  public resource_acquisition getResource_acquisition() {
    return ((PropertyOne<resource_acquisition>)getProperty("resource_acquisition")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
}
