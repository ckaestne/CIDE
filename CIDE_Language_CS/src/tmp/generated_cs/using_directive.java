package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class using_directive extends GenASTNode {
  public using_directive(type_name type_name, using_directiveEnd using_directiveEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyOne<using_directiveEnd>("using_directiveEnd", using_directiveEnd)
    }, firstToken, lastToken);
  }
  public using_directive(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new using_directive(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public using_directiveEnd getUsing_directiveEnd() {
    return ((PropertyOne<using_directiveEnd>)getProperty("using_directiveEnd")).getValue();
  }
}
