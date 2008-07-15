package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ObjectLiteral extends GenASTNode {
  public ObjectLiteral(PropertyNameAndValueList propertyNameAndValueList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<PropertyNameAndValueList>("propertyNameAndValueList", propertyNameAndValueList)
    }, firstToken, lastToken);
  }
  public ObjectLiteral(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ObjectLiteral(cloneProperties(),firstToken,lastToken);
  }
  public PropertyNameAndValueList getPropertyNameAndValueList() {
    return ((PropertyZeroOrOne<PropertyNameAndValueList>)getProperty("propertyNameAndValueList")).getValue();
  }
}
