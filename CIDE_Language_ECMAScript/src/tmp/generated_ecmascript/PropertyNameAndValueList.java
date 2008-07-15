package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PropertyNameAndValueList extends GenASTNode {
  public PropertyNameAndValueList(PropertyNameAndValue propertyNameAndValue, ArrayList<PropertyNameAndValueListEnd> propertyNameAndValueListEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PropertyNameAndValue>("propertyNameAndValue", propertyNameAndValue),
      new PropertyZeroOrMore<PropertyNameAndValueListEnd>("propertyNameAndValueListEnd", propertyNameAndValueListEnd)
    }, firstToken, lastToken);
  }
  public PropertyNameAndValueList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PropertyNameAndValueList(cloneProperties(),firstToken,lastToken);
  }
  public PropertyNameAndValue getPropertyNameAndValue() {
    return ((PropertyOne<PropertyNameAndValue>)getProperty("propertyNameAndValue")).getValue();
  }
  public ArrayList<PropertyNameAndValueListEnd> getPropertyNameAndValueListEnd() {
    return ((PropertyZeroOrMore<PropertyNameAndValueListEnd>)getProperty("propertyNameAndValueListEnd")).getValue();
  }
}
