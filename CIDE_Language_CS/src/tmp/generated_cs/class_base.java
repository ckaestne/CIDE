package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_base extends GenASTNode {
  public class_base(class_type class_type, ArrayList<type_name> type_name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<class_type>("class_type", class_type),
      new PropertyZeroOrMore<type_name>("type_name", type_name)
    }, firstToken, lastToken);
  }
  public class_base(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_base(cloneProperties(),firstToken,lastToken);
  }
  public class_type getClass_type() {
    return ((PropertyOne<class_type>)getProperty("class_type")).getValue();
  }
  public ArrayList<type_name> getType_name() {
    return ((PropertyZeroOrMore<type_name>)getProperty("type_name")).getValue();
  }
}
