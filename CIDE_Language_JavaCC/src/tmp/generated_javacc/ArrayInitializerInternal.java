package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayInitializerInternal extends GenASTNode {
  public ArrayInitializerInternal(VariableInitializer variableInitializer, ArrayList<VariableInitializer> variableInitializer1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableInitializer>("variableInitializer", variableInitializer),
      new PropertyZeroOrMore<VariableInitializer>("variableInitializer1", variableInitializer1)
    }, firstToken, lastToken);
  }
  public ArrayInitializerInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayInitializerInternal(cloneProperties(),firstToken,lastToken);
  }
  public VariableInitializer getVariableInitializer() {
    return ((PropertyOne<VariableInitializer>)getProperty("variableInitializer")).getValue();
  }
  public ArrayList<VariableInitializer> getVariableInitializer1() {
    return ((PropertyZeroOrMore<VariableInitializer>)getProperty("variableInitializer1")).getValue();
  }
}
