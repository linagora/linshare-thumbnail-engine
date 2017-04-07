class A
{
public:
    int getValue() const { return this->value; } // définition au sein de la classe
    void setValue(int value);
    void print() const;
private:
    int value;
};

inline void A::setValue(int value) // définition en dehors de la classe (inline)
{
    this->value = value;
}

void A::print() const // définition en dehors de la classe (non inline)
{
    std::cout << "Value=" << this->value << std::endl;
}
