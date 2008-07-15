package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PropertyNameAndValueListEnd1 extends PropertyNameAndValueListEnd {
  public PropertyNameAndValueListEnd1(PropertyNameAndValue propertyNameAndValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PropertyNameAndValue>("propertyNameAndValue", propertyNameAndValue)
    }, firstToken, lastToken);
  }
  public PropertyNameAndValueListEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PropertyNameAndValueListEnd1(cloneProperties(),firstToken,lastToken);
  }
  public PropertyNameAndValue getPropertyNameAndValue() {
    return ((PropertyOne<PropertyNameAndValue>)getProperty("propertyNameAndValue")).getValue();
  }
}
