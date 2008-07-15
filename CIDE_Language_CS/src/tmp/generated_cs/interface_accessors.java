package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_accessors extends GenASTNode {
  public interface_accessors(interface_accessor interface_accessor, interface_accessor interface_accessor1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_accessor>("interface_accessor", interface_accessor),
      new PropertyZeroOrOne<interface_accessor>("interface_accessor1", interface_accessor1)
    }, firstToken, lastToken);
  }
  public interface_accessors(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_accessors(cloneProperties(),firstToken,lastToken);
  }
  public interface_accessor getInterface_accessor() {
    return ((PropertyOne<interface_accessor>)getProperty("interface_accessor")).getValue();
  }
  public interface_accessor getInterface_accessor1() {
    return ((PropertyZeroOrOne<interface_accessor>)getProperty("interface_accessor1")).getValue();
  }
}
