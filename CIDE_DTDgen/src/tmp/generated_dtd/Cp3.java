package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Cp3 extends Cp {
  public Cp3(SeqOrChoice seqOrChoice, Modifier modifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SeqOrChoice>("seqOrChoice", seqOrChoice),
      new PropertyZeroOrOne<Modifier>("modifier1", modifier1)
    }, firstToken, lastToken);
  }
  public Cp3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Cp3(cloneProperties(),firstToken,lastToken);
  }
  public SeqOrChoice getSeqOrChoice() {
    return ((PropertyOne<SeqOrChoice>)getProperty("seqOrChoice")).getValue();
  }
  public Modifier getModifier1() {
    return ((PropertyZeroOrOne<Modifier>)getProperty("modifier1")).getValue();
  }
}
