package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class resource_acquisition1 extends resource_acquisition {
  public resource_acquisition1(local_variable_declaration local_variable_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_variable_declaration>("local_variable_declaration", local_variable_declaration)
    }, firstToken, lastToken);
  }
  public resource_acquisition1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new resource_acquisition1(cloneProperties(),firstToken,lastToken);
  }
  public local_variable_declaration getLocal_variable_declaration() {
    return ((PropertyOne<local_variable_declaration>)getProperty("local_variable_declaration")).getValue();
  }
}
